package fr.uga.l3miage.integrator.cyberCommandes.controllers;


import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;

import java.util.*;

import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.services.TourneeService;
import io.swagger.v3.oas.annotations.media.Schema;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void clear() {
        this.tourneeRepository.deleteAll();
    }



    @Test
    void getAllTourneesTest() {
        final HttpHeaders headers = new HttpHeaders();
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
     ResponseEntity<List<TourneeResponseDTO>> response2 = template
                .exchange(
                        "/api/v1/tournees/?reference=J1",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<TourneeResponseDTO>>() {},
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
        final HttpHeaders headers = new HttpHeaders();

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
      //  assertThat(response.getBody()).isNotNull();

    }



}
