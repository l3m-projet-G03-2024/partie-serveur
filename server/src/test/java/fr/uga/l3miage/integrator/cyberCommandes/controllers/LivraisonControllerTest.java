package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Autowired
    private TourneeRepository tourneeRepository;

    @AfterEach
    public void clear() {
        this.livraisonRepository.deleteAll();
    }



    @Test
    void getLivraisonFound() {
        final HttpHeaders headers = new HttpHeaders();
        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("etat", "EFFECTUEE");

        LivraisonEntity livraison1 = LivraisonEntity.builder()
                .reference("LIV1")
                .etat(EtatsDeLivraison.EFFECTUEE)
                .build();
        LivraisonEntity livraison2 = LivraisonEntity.builder()
                .reference("LIV2")
                .etat(EtatsDeLivraison.EFFECTUEE)
                .build();
        List<LivraisonEntity> livraisonEntities = new ArrayList<>();
        livraisonEntities.add(livraison1);
        livraisonEntities.add(livraison2);

        livraisonRepository.saveAll(livraisonEntities);
        when(livraisonComponent.findLivraisonByEtat(EtatsDeLivraison.EFFECTUEE)).thenReturn(livraisonEntities);

        ResponseEntity<List<LivraisonResponseDTO>> response = testRestTemplate.exchange(
                "/api/livraisons/?etat=EFFECTUEE",
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<LivraisonResponseDTO>>() {}
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void canCreateLivraison() {
        // Given
        final HttpHeaders headers = new HttpHeaders();

        List<LivraisonTourneeRequest> livraisons = new ArrayList<>();

        TourneeEntity tourneeEntity1 = TourneeEntity
                .builder()
                .reference("T1")
                .build();
        TourneeEntity tourneeEntity2 = TourneeEntity
                .builder()
                .reference("T2")
                .build();
        tourneeRepository.save(tourneeEntity1);
        tourneeRepository.save(tourneeEntity2);

        LivraisonTourneeRequest livraison1 = LivraisonTourneeRequest
                .builder()
                .ordre(1)
                .reference("LV1")
                .referenceTournee(tourneeEntity1.getReference())
                .build();
        LivraisonTourneeRequest livraison2 = LivraisonTourneeRequest
                .builder()
                .ordre(2)
                .reference("LV2")
                .referenceTournee(tourneeEntity2.getReference())
                .build();
        livraisons.add(livraison1);
        livraisons.add(livraison2);

        final  LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest = LivraisonsCreationTourneeRequest
                .builder()
                .livraisons(livraisons)
                .build();

        // when

        ResponseEntity<LivraisonCreationResponseDTO> response = testRestTemplate
                .exchange("/api/livraisons/",
                        HttpMethod.POST,
                        new HttpEntity<>(livraisonsCreationTourneeRequest,headers),
                        LivraisonCreationResponseDTO.class);
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }
}
