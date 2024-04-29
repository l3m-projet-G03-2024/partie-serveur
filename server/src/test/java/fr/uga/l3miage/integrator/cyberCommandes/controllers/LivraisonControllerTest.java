package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")

public class LivraisonControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private LivraisonRepository livraisonRepository;
    @SpyBean
    private LivraisonComponent livraisonComponent;

    @AfterEach
    public void clear(){
        this.livraisonRepository.deleteAll();
    }

    @Test
    void getLivraisonFound() {
        //get
        when(livraisonComponent.findLivraisonByEtat(EtatsDeLivraison.EFFECTUEE)).thenReturn(Collections.emptyList());

        //When
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/livraisons?etat=ENPARCOURS", String.class);

        //Then

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
    /*void getAllTourneeByEtatOrReferenceJourneeNotFound(){
         final  HttpHeaders headers = new HttpHeaders();
         final Map<String, Object> urlParams = new HashMap<>();
         urlParams.put("journee", "il n'existe aucune journée qui a MN comme etat");
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENPARCOURS,null)).thenReturn(Collections.emptyList());

        // When
        ResponseEntity<NotFoundErrorResponse> response = template.exchange("/api/tournees?etat=MN",
                HttpMethod.GET, new HttpEntity<>(null, headers), NotFoundErrorResponse.class, urlParams);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }*/

    @Test
    void getLivraisonNotFound() {
        final  HttpHeaders headers = new HttpHeaders();
        final Map<String, Object> urlParams = new HashMap<>();

        urlParams.put("livraisons", "il n'existe aucune livaison qui a EM comme etat");
        when(livraisonComponent.findLivraisonByEtat(EtatsDeLivraison.EFFECTUEE)).thenReturn(Collections.emptyList());

        //when

        ResponseEntity<NotFoundErrorResponse> response = testRestTemplate.exchange("/api/livraisons?etat=MP",
                HttpMethod.GET, new HttpEntity<>(null, headers), NotFoundErrorResponse.class, urlParams);

        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}
