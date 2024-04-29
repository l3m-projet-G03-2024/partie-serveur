package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeServiceTest {
    @Autowired 
    private TourneeService tourneeService;

    @MockBean
    private TourneeComponent tourneeComponent;

    @MockBean
    private JourneeRepository journeeRepository;




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


        when(journeeRepository.findByReference(journee1.getReference())).thenReturn(journee1); // On retourne la journée simulée lorsque findByReference est appelé
        when(tourneeComponent.createTournees(List.of(tourneeEntity1, tourneeEntity2))).thenReturn(List.of(tourneeEntity1, tourneeEntity2));

        TourneeCreationResponseDTO tourneeCreationResponseDTO = tourneeService.createTournee(
                tourneeCreationRequests,
                journee1.getReference()
        ); // On appelle la méthode à tester


        assertThat(tourneeCreationResponseDTO.isSuccess()).isTrue();
        assertThat(tourneeCreationResponseDTO.getMessage()).isEqualTo("Toutes les tournées ont été créés avec succès");

    }

}
