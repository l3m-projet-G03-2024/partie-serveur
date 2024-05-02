package fr.uga.l3miage.integrator.cyberRessources.controllers;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")

public class CamionControllerTest {
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;

    @AfterEach
    public void clear() {
        this.camionRepository.deleteAll();
    }




    @Test
    void getCamionFound() {
            // Créer un entrepot
            EntrepotEntity entrepotEntity = EntrepotEntity.builder()
                    .nom("E1").build();
            entrepotRepository.save(entrepotEntity);

            // CamionEntity est dans entrepotEntity
            CamionEntity camionEntity = CamionEntity.builder()
                    .immatriculation("A100")
                    .longitude(1.9)
                    .latitude(2.0)
                    .entrepot(entrepotEntity)
                    .build();
            camionRepository.save(camionEntity);
            final HttpHeaders headers = new HttpHeaders();

            final Map<String, Object> urlParams = new HashMap<>();
            urlParams.put("nom", "E1");


            ResponseEntity<List<CamionEntity>> response = template.exchange(
                    "/api/camions/{nom}",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<CamionEntity>>() {},
                    urlParams
            );
            List<CamionEntity> camions = new ArrayList<>();
            camions.add(camionEntity);

            // Assertions
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

            assertThat(response.getBody().size()).isEqualTo(1);
        }

    }




