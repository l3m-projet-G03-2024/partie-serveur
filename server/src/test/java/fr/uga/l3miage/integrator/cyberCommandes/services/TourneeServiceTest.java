package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.stream.Collectors;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.TourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.CamionNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.CamionImmatriculationTouneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import org.springframework.boot.test.mock.mockito.SpyBean;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeServiceTest {
    @Autowired 
    private TourneeService tourneeService;

    @MockBean
    private TourneeComponent tourneeComponent;

    @Autowired
    private JourneeRepository journeeRepository;

    @SpyBean
    private TourneeMapper tourneeMapper;

    @MockBean
    private CamionRepository camionRepository;




    @Test 
    void RequestTourneeByEtatOrReferenceJournee(){

        JourneeEntity journee1 = JourneeEntity.builder()
                .reference("1Ab")
                .build();

        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
            .reference("1T")
                .journee(journee1)
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity2 = TourneeEntity.builder()
            .reference("2T")
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity3 = TourneeEntity.builder()
            .reference("3T")
            .etat(EtatsDeTournee.EFFECTUEE)
            .build();



        // Création d'une liste de tournées simulées
        List<TourneeEntity> tourneeEntities = new ArrayList<>();
        tourneeEntities.add(tourneeEntity1);
        tourneeEntities.add(tourneeEntity2);
        tourneeEntities.add(tourneeEntity3);
        // Configuration du mock pour retourner la liste simulée lorsque findAllTournee est appelé
        when(tourneeComponent.findAllTournee()).thenReturn(tourneeEntities);

        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,null)).thenReturn(List.of(tourneeEntity1, tourneeEntity2));

        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(null,journee1.getReference())).thenReturn(List.of(tourneeEntity1));

        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,journee1.getReference())).thenReturn(List.of(tourneeEntity1));

        when(tourneeComponent.findAllTournee()).thenReturn(tourneeEntities);

        // Appel de la méthode à tester en spécifiant seulement l'état
        List<TourneeResponseDTO> tourneeResponseDTOs = tourneeService.getTourneesByEtatsOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,null);
        assertEquals(2, tourneeResponseDTOs.size());

        // Appel de la méthode à tester en spécifiant ENCHARGEMENT comme état et en specifiant la reference de la journée 1
        List<TourneeResponseDTO> tourneeResponseDTOsEnChargement = tourneeService.getTourneesByEtatsOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,tourneeEntity1.getJournee().getReference());
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(1, tourneeResponseDTOsEnChargement.size());


        // Appel de la méthode à tester en specifiant seulement la reference de la journée 1
        List<TourneeResponseDTO> tourneeResponseDTOsJournee1 = tourneeService.getTourneesByEtatsOrReferenceJournee(null,tourneeEntity1.getJournee().getReference());
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(1, tourneeResponseDTOsJournee1.size());


        // Appel de la méthode à tester
        List<TourneeResponseDTO> tourneeResponseDTOsAll = tourneeService.getTourneesByEtatsOrReferenceJournee(null,null);
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(3, tourneeResponseDTOsAll.size());





    }

    @Test
    void CreatTourneeSuccess() {
        // On cree une tournée creation request avec une distance, une référence et un état
        TourneeCreationRequest tourneeCreationRequest1 = TourneeCreationRequest.builder()
                .distance(7.00)
                .etat(EtatsDeTournee.EFFECTUEE)
                .reference("T1")
                .build();
        // On cree une tournée creation avec une référence et un état
        TourneeCreationRequest tourneeCreationRequest2 = TourneeCreationRequest.builder()
                .distance(5.00)
                .etat(EtatsDeTournee.EFFECTUEE)
                .reference("T2")
                .build();
        // On crée une liste de requêtes de création de tournée
        List<TourneeCreationRequest> tourneeCreationRequests = Arrays.asList(tourneeCreationRequest1,
                tourneeCreationRequest2);
        // On crée une journée
        JourneeEntity journee1 = JourneeEntity.builder()
                .reference("1Ab")
                .build();
        journeeRepository.save(journee1);

        TourneesCreationBodyRequest tourneesCreationBodyRequest = TourneesCreationBodyRequest
                .builder()
                .tournees(tourneeCreationRequests)
                .referenceJournee(journee1.getReference())
                .build();
        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
                .reference("T1")
                .etat(EtatsDeTournee.EFFECTUEE)
                .distance(5.00)
                .build();

        TourneeEntity tourneeEntity2 = TourneeEntity.builder()
                .distance(5.00)
                .etat(EtatsDeTournee.EFFECTUEE)
                .reference("T2")
                .build();


  //      when(journeeRepository.findByReference(journee1.getReference())).thenReturn(journee1); // On retourne la journée simulée lorsque findByReference est appelé
        //when(journeeComponent)
        when(tourneeComponent.createTournees(List.of(tourneeEntity1, tourneeEntity2))).thenReturn(List.of(tourneeEntity1, tourneeEntity2));

       TourneeCreationResponseDTO tourneeCreationResponseDTO = tourneeService.createTournees(tourneesCreationBodyRequest); // On appelle la méthode à tester


        assertThat(tourneeCreationResponseDTO.isSuccess()).isTrue();
        assertThat(tourneeCreationResponseDTO.getMessage()).isEqualTo("Toutes les tournées ont été créés avec succès");

    }

    @Test
    void canAddEmployeInTournee() throws TourneeNotFoundException, NotFoundEmployeEntityException {

        EmployeEntity employe = EmployeEntity.builder()
                .trigramme("test1")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .tourneeEntities(new HashSet<>())
                .build();

        TourneeEntity tourneeEntity = TourneeEntity.builder()
                .reference("test")
                .distance(7.00)
                .etat(EtatsDeTournee.PLANIFIEE)
                .tdrEffectif(1)
                .tdrTheorique(1)
                .employes(new HashSet<>())
                .build();
        when(tourneeComponent.addEmployeInTournee(any(String.class), any(String.class)))
                .thenReturn(tourneeEntity);

        TourneeResponseDTO responseDTO = tourneeService.addEmployeInTournee(tourneeEntity.getReference(), employe.getTrigramme());

        employe.getTourneeEntities().add(tourneeEntity);
        tourneeEntity.getEmployes().add(employe);
        TourneeResponseDTO expectedResponseDTO = tourneeMapper.toTourneeResponseDTO(tourneeEntity);

//        assertThat(responseDTO).usingRecursiveComparison().isEqualTo(expectedResponseDTO);
        assertTrue(tourneeEntity.getEmployes().contains(employe));
    }

    @Test
    void getAllTourneesByEmployeId() throws NotFoundEmployeEntityException {

        EmployeEntity employe = EmployeEntity.builder()
                .trigramme("test")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .tourneeEntities(new HashSet<>())
                .build();

        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
                .reference("test1")
                .distance(7.00)
                .etat(EtatsDeTournee.PLANIFIEE)
                .tdrEffectif(1)
                .tdrTheorique(1)
                .employes(new HashSet<>())
                .build();
        tourneeEntity1.getEmployes().add(employe);
        employe.getTourneeEntities().add(tourneeEntity1);

        TourneeEntity tourneeEntity2 = TourneeEntity.builder()
                .reference("test2")
                .distance(7.00)
                .etat(EtatsDeTournee.PLANIFIEE)
                .tdrEffectif(1)
                .tdrTheorique(1)
                .employes(new HashSet<>())
                .build();
        tourneeEntity2.getEmployes().add(employe);
        employe.getTourneeEntities().add(tourneeEntity2);

        TourneeEntity tourneeEntity3 = TourneeEntity.builder()
                .reference("test3")
                .distance(7.00)
                .etat(EtatsDeTournee.PLANIFIEE)
                .tdrEffectif(1)
                .tdrTheorique(1)
                .employes(new HashSet<>())
                .build();
        Set<TourneeEntity> tourneeEntitiesByEmploi = new HashSet<>();
        tourneeEntitiesByEmploi.add(tourneeEntity1);
        tourneeEntitiesByEmploi.add(tourneeEntity2);

        when(tourneeComponent.getAllTourneesByEmployeId(any(String.class))).thenReturn(Set.of(tourneeEntity1, tourneeEntity2));

        Set<TourneeResponseDTO> tourneeResponseDTOS = tourneeService.getAllTourneesByEmployeId("test");
        Set<TourneeEntity> tourneeEntities = employe.getTourneeEntities();

        Set<TourneeResponseDTO> dtoSetExpected = tourneeEntitiesByEmploi.stream()
                .map(tourneeMapper::toTourneeResponseDTO)
                .collect(Collectors.toSet());

        assertEquals(tourneeEntities.size(), tourneeResponseDTOS.size());
        assertThat(tourneeResponseDTOS).usingRecursiveComparison().isEqualTo(dtoSetExpected);
        assertEquals(2, dtoSetExpected.size());
    }

    void getTourneeFound() throws TourneeNotFoundException {
        // Given
        TourneeEntity tourneeEntity = TourneeEntity
                .builder()
                .reference("T1")
                .build();

        when(tourneeComponent.findTourneeByReference(anyString())).thenReturn(tourneeEntity) ;
        TourneeResponseDTO expectedResponse = tourneeMapper.toTourneeResponseDTO(tourneeEntity) ;

        // when
        TourneeResponseDTO actualResponse = tourneeService.getTournee(tourneeEntity.getReference()) ;

        // then
        assertNotNull(actualResponse);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(actualResponse) ;
        verify(tourneeComponent, times(1)).findTourneeByReference(anyString()) ;
        verify(tourneeMapper,times(2)).toTourneeResponseDTO(tourneeEntity) ;
    }

    @Test
    void getTourneeNotFound() throws TourneeNotFoundException {
        // Given
        when(tourneeComponent.findTourneeByReference(anyString())).thenThrow(TourneeNotFoundException.class) ;
        // when - then
        assertThrows(TourneeNotFoundRestException.class, () -> tourneeService.getTournee(anyString())) ;
    }

    @Test
    void AddingCamionOnTourneeTest() throws TourneeNotFoundException, CamionNotFoundException {
        // Arrange
        CamionImmatriculationTouneeRequest camionImmatriculationTouneeRequest = CamionImmatriculationTouneeRequest.builder()
                .immatriculation("C1")
                .build();
        CamionEntity camionEntity = CamionEntity.builder()
                .immatriculation("C1")
                .build();

        TourneeEntity tourneeEntity = TourneeEntity.builder()
                .reference("T1")
                .build();

        CamionResponseDTO camionResponseDTO = CamionResponseDTO.builder()
                .immatriculation("T1")
                .build();
        AddCamionOnTourneeResponseDTO responseDTO = AddCamionOnTourneeResponseDTO.builder()
                .camion(camionResponseDTO)
                .reference("T1")
                .build();

        when(tourneeComponent.findTourneeByReference("T1")).thenReturn(tourneeEntity);
        when(camionRepository.findById("C1")).thenReturn(Optional.ofNullable(camionEntity));
        when(tourneeComponent.addingTourneeAfterAddedCamion(tourneeEntity)).thenReturn(tourneeEntity);
        when(tourneeMapper.toTourneeCamionResponseDTO(tourneeEntity)).thenReturn(responseDTO);

        // Act
        AddCamionOnTourneeResponseDTO actualResponseDTO = tourneeService.addCamionOnTournee("T1", camionImmatriculationTouneeRequest);

        // Assert
        Assertions.assertThat(actualResponseDTO).isNotNull();
        Assertions.assertThat(actualResponseDTO.getCamion()).isEqualTo(camionResponseDTO);
        Assertions.assertThat(actualResponseDTO.getReference()).isEqualTo("T1");

    }
}
