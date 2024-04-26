package fr.uga.l3miage.integrator.cyberCommandes.controllers;


import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TourneeControllerTest {
    @Autowired
    private  TestRestTemplate template;
    @Autowired
    private TourneeRepository tourneeRepository;
    @SpyBean
    private TourneeComponent tourneeComponent;

    @AfterEach
    public void clear(){
        this.tourneeRepository.deleteAll();
    }
    @Test
    void getAllTourneeByEtatOrReferenceJourneeFound(){
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENPARCOURS,null)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<String> response = template.getForEntity("/api/v1/tournees?etat=ENPARCOURS", String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void getAllTourneeByEtatOrReferenceJourneeNotFound(){
         final  HttpHeaders headers = new HttpHeaders();
         final Map<String, Object> urlParams = new HashMap<>();
         urlParams.put("journee", "il n'existe aucune journée qui a MN comme etat");
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENPARCOURS,null)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<NotFoundErrorResponse> response = template.exchange("/api/tournees?etat=MN",
                HttpMethod.GET, new HttpEntity<>(null, headers), NotFoundErrorResponse.class, urlParams);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }



}
