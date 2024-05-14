package fr.uga.l3miage.integrator.cyberCommandes.controllers;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import fr.uga.l3miage.integrator.cyberCommandes.errors.TourneeNotFoundResponse;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.CamionImmatriculationTouneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

import fr.uga.l3miage.integrator.cyberCommandes.services.TourneeService;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
import fr.uga.l3miage.integrator.cyberRessources.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;


import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TourneeControllerTest {
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private TourneeRepository tourneeRepository;
    @SpyBean
    private TourneeComponent tourneeComponent;
    @SpyBean
    private TourneeService tourneeService;
    @SpyBean
    private TourneeMapper tourneeMapper;
    @Autowired
    private JourneeRepository journeeRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @SpyBean
    private EmployeMapper employeMapper;

    @Autowired
    private CamionRepository camionRepository;



    private String accessToken;

    private final HttpHeaders headers = new HttpHeaders();



    @BeforeEach
    public void setup() {
        template.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        {
            try {
                File file = ResourceUtils.getFile("classpath:accessToken.txt");
                accessToken = new String(Files.readAllBytes(Paths.get(file.getPath())));
                headers.set("AuthorizationTest", "Test "+accessToken);
                // System.out.println(accessToken);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void getAllTourneesTest() {
        final Map<String, Object> urlParams1 = new HashMap<>();

        urlParams1.put("etat", EtatsDeTournee.EFFECTUEE);

        final Map<String, Object> urlParams2 = new HashMap<>();
        urlParams2.put("reference", "J1");


        JourneeEntity journee  = JourneeEntity
                .builder()
                .reference("J1")
                .build();
        journeeRepository.save(journee);

        TourneeEntity tourneeEntity1 = TourneeEntity
                .builder()
                .etat(EtatsDeTournee.EFFECTUEE)
                .reference("T1")
                .journee(journee)
                .build();
        TourneeEntity tourneeEntity2 = TourneeEntity
                .builder()
                .reference("T2")
                .etat(EtatsDeTournee.EFFECTUEE)
                .build();

        Set<TourneeEntity> tournees = new HashSet<>();
        tournees.add(tourneeEntity1);
        tournees.add(tourneeEntity2);
        tourneeRepository.saveAll(tournees);

        journee.setTournees(tournees);
        // When
     ResponseEntity<List<TourneeResponseDTO>> response1 = template
                .exchange(
                        "/api/v1/tournees/?etat=EFFECTUEE",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<TourneeResponseDTO>>() {}
                );
     ResponseEntity<Set<TourneeResponseDTO>> response2 = template
                .exchange(
                        "/api/v1/tournees/?reference=J1",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<Set<TourneeResponseDTO>>() {},
                        urlParams2
                );
        // Then
        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);


        // Vérifie si la réponse contient des données
        assertThat(response1.getBody()).isNotEmpty();
        assertThat(response1.getBody()).hasSize(2); // Nombre de tournees dans l'état EFFECTUEE
        assertThat(response2.getBody()).isNotEmpty();;
        assertThat(response2.getBody()).hasSize(1); // Nombre de tournees associées à la référence de la journée "J1"

    }




    @Test
    void canCreateTournee() {
        // Given

        List<TourneeCreationRequest> tournees = new ArrayList<>();
         TourneeCreationRequest tourneeCreationRequest1 = TourneeCreationRequest
                .builder()
                .reference("T1")
                .etat(EtatsDeTournee.ENPARCOURS)
                .build();

        TourneeCreationRequest tourneeCreationRequest2 = TourneeCreationRequest
                .builder()
                .reference("T2")
                .etat(EtatsDeTournee.ENPARCOURS)
                .build();

        tournees.add(tourneeCreationRequest1);
        tournees.add(tourneeCreationRequest2);


        String referenceJournee = "J1";

        JourneeEntity journee = JourneeEntity
                .builder()
                .reference(referenceJournee)
                .build();
        journeeRepository.save(journee);

        final TourneesCreationBodyRequest tourneesCreationBodyRequest = TourneesCreationBodyRequest
                .builder()
                .referenceJournee(journee.getReference())
                .tournees(tournees)
                .build();

        // When
        ResponseEntity<TourneeCreationResponseDTO> response = template
                .exchange("/api/v1/tournees/",
                HttpMethod.POST,
                new HttpEntity<>(tourneesCreationBodyRequest,headers),
                TourneeCreationResponseDTO.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

//    @Test
//    void canAddEmployeInTournee() {
//        // Given
//        final HttpHeaders headers = new HttpHeaders();
//        final EmployeEntity employe = EmployeEntity.builder()
//                .trigramme("test1")
//                .email("test1@gmail.com")
//                .nom("nom")
//                .prenom("prenom")
//                .telephone("1234567890")
//                .emploi(Emploi.LIVREUR)
//                .tourneeEntities(new HashSet<>())
//                .build();
//
//        final TourneeEntity tourneeEntity = TourneeEntity.builder()
//                .reference("test")
//                .distance(7.00)
//                .etat(EtatsDeTournee.PLANIFIEE)
//                .tdrEffectif(1)
//                .tdrTheorique(1)
//                .employes(new HashSet<>())
//                .build();
//        employeRepository.save(employe);
//        tourneeRepository.save(tourneeEntity);
//
//        // When
//        ResponseEntity<TourneeResponseDTO> response = template
//                .exchange("/api/v1/tournees/{referenceTournee}/add-employe",
//                        HttpMethod.PATCH,
//                        new HttpEntity<>(employe.getTrigramme(), headers),
//                        TourneeResponseDTO.class,
//                        tourneeEntity.getReference());
//
//        // Then
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
//    }

//    @Test
//    void getAllTourneesByEmployeEmail() throws NotFoundEmployeEntityException {
//
//        EmployeEntity employe = EmployeEntity.builder()
//                .trigramme("test")
//                .email("test1@gmail.com")
//                .nom("nom")
//                .prenom("prenom")
//                .telephone("1234567890")
//                .emploi(Emploi.LIVREUR)
//                .tourneeEntities(new HashSet<>())
//                .build();
//
//        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
//                .reference("test1")
//                .distance(7.00)
//                .etat(EtatsDeTournee.PLANIFIEE)
//                .tdrEffectif(1)
//                .employes(new HashSet<>())
//                .build();
//        tourneeEntity1.getEmployes().add(employe);
//        employe.getTourneeEntities().add(tourneeEntity1);
//
//        TourneeEntity tourneeEntity2 = TourneeEntity.builder()
//                .reference("test2")
//                .distance(7.00)
//                .etat(EtatsDeTournee.PLANIFIEE)
//                .tdrEffectif(1)
//                .employes(new HashSet<>())
//                .build();
//        tourneeEntity2.getEmployes().add(employe);
//        employe.getTourneeEntities().add(tourneeEntity2);
//
//        TourneeEntity tourneeEntity3 = TourneeEntity.builder()
//                .reference("test3")
//                .distance(7.00)
//                .etat(EtatsDeTournee.PLANIFIEE)
//                .tdrEffectif(1)
//                .employes(new HashSet<>())
//                .build();
//        Set<TourneeEntity> tourneeEntitiesByEmploi = new HashSet<>();
//        tourneeEntitiesByEmploi.add(tourneeEntity1);
//        tourneeEntitiesByEmploi.add(tourneeEntity2);
//
//        when(tourneeComponent.getAllTourneesByEmployeEmail(any(String.class))).thenReturn(Set.of(tourneeEntity1, tourneeEntity2));
//
//        Set<TourneeResponseDTO> tourneeResponseDTOS = tourneeService.getAllTourneesByEmployeEmail("test1@gmail.com");
//        Set<TourneeEntity> tourneeEntities = employe.getTourneeEntities();
//
//        Set<TourneeResponseDTO> dtoSetExpected = tourneeEntitiesByEmploi.stream()
//                .map(tourneeMapper::toTourneeResponseDTO)
//                .collect(Collectors.toSet());
//
//        assertEquals(tourneeEntities.size(), tourneeResponseDTOS.size());
//        assertThat(tourneeResponseDTOS).usingRecursiveComparison().isEqualTo(dtoSetExpected);
//        assertEquals(2, dtoSetExpected.size());
//    }

    @Test
    void getTourneeNotFound() {
        // Given


        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("referenceTournee", "ma tournee qui n'existe pas");

        TourneeNotFoundResponse tourneeNotFoundResponseExpected = TourneeNotFoundResponse
                .builder()
                .referenceTournee("ma tournee qui n'existe pas")
                .uri("/api/v1/tournees/ma%20tournee%20qui%20n%27existe%20pas")
                .errorMessage("Tournée non trouvée pour la référence : ma tournee qui n'existe pas")
                .build();

        // when
        ResponseEntity<TourneeNotFoundResponse> response = template.exchange("/api/v1/tournees/{referenceTournee}", HttpMethod.GET, new HttpEntity<>(null, headers),TourneeNotFoundResponse.class, urlParams);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).usingRecursiveComparison()
                .isEqualTo(tourneeNotFoundResponseExpected) ;
    }

    @Test
    void getTourneeFound() {
        // Given
        final String refTournee = "T1" ;



        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("T1", refTournee);

        TourneeResponseDTO tourneeResponseDTOExpected = TourneeResponseDTO
                .builder()
                .reference(refTournee)
                .etat(EtatsDeTournee.ENPARCOURS)
                .build();

        doReturn(tourneeResponseDTOExpected).when(tourneeService).getTournee(refTournee) ;

        // when
        ResponseEntity<TourneeResponseDTO> response = template.exchange("/api/v1/tournees/{T1}", HttpMethod.GET, new HttpEntity<>(null, headers), TourneeResponseDTO.class, urlParams) ;

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK) ;
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(tourneeResponseDTOExpected) ;
    }

    @Test
    void AddingCamionTestOK() {


        // URL parameters
        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("referenceTournee", "1T");

        // Request payload
        CamionImmatriculationTouneeRequest camionImmatriculationTouneeRequest = CamionImmatriculationTouneeRequest
                .builder()
                .immatriculation("A123")
                .build();
        CamionEntity camionEntity = CamionEntity.builder()
                .immatriculation("A123")
                .build();

        camionRepository.save(camionEntity);


        // Tournee entity
        TourneeEntity tourneeEntity = TourneeEntity
                .builder()
                .reference("1T")
                .distance(7.00)
                .etat(EtatsDeTournee.EFFECTUEE)
                .build();
        tourneeRepository.save(tourneeEntity);


        // Prepare the request body
        HttpEntity<CamionImmatriculationTouneeRequest> requestEntity = new HttpEntity<>(camionImmatriculationTouneeRequest, headers);

        // Make the PATCH request
        ResponseEntity<AddCamionOnTourneeResponseDTO> response = template
                .exchange("/api/v1/tournees/{referenceTournee}/add-camion",
                        HttpMethod.PATCH,
                        requestEntity,
                        AddCamionOnTourneeResponseDTO.class,
                        urlParams);

        // Expected response data
        CamionResponseDTO camionResponseDTO = CamionResponseDTO.builder()
                .immatriculation("A123")
                .build();
        AddCamionOnTourneeResponseDTO expectedResponse = AddCamionOnTourneeResponseDTO
                .builder()
                .camion(camionResponseDTO)
                .reference("1T")
                .build();

        // Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

}
