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
import io.swagger.v3.oas.annotations.media.Schema;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    private TourneeMapper tourneeMapper;
    @Autowired
    private JourneeRepository journeeRepository;

    @AfterEach
    public void clear() {
        this.tourneeRepository.deleteAll();
    }

    @Test
    void getAllTourneeByEtatOrReferenceJourneeFound() {
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENPARCOURS, null))
                .thenReturn(Collections.emptyList());

        // When
        ResponseEntity<String> response = template.getForEntity("/api/v1/tournees?etat=ENPARCOURS", String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllTourneeByEtatOrReferenceJourneeNotFound() {
        final HttpHeaders headers = new HttpHeaders();
        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("journee", "il n'existe aucune journée qui a MN comme etat");
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENPARCOURS, null))
                .thenReturn(Collections.emptyList());

        // When
        ResponseEntity<NotFoundErrorResponse> response = template.exchange("/api/tournees?etat=MN",
                HttpMethod.GET, new HttpEntity<>(null, headers), NotFoundErrorResponse.class, urlParams);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void getAllTourneeFound() {
        //given
        when(tourneeComponent.findAllTournee()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<String> response = template.getForEntity("/api/v1/tournees", String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllTourneeNotFound() {
        final HttpHeaders headers = new HttpHeaders();
        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("tournees", "il n'existe aucune tournee");

        when(tourneeComponent.findAllTournee()).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<NotFoundErrorResponse> response = template.exchange("/api/tournees?etat=MN",
                HttpMethod.GET, new HttpEntity<>(null, headers), NotFoundErrorResponse.class, urlParams);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
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

        // Add the items to the list
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
                .tourneeCreationRequests(tournees)
                .build();

        // When
        ResponseEntity<TourneeCreationResponseDTO> response = template
                .exchange("/api/v1/tournees",
                HttpMethod.POST,
                new HttpEntity<>(tourneesCreationBodyRequest,headers),
                TourneeCreationResponseDTO.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
      //  assertThat(response.getBody()).isNotNull();

    }



}
