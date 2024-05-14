package fr.uga.l3miage.integrator.cyberRessources.controllers;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

            ResponseEntity<List<CamionResponseDTO>> response = template.exchange(
                    "/api/v1/camions/?nomEntrepot=E1",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    new ParameterizedTypeReference<List<CamionResponseDTO>>() {}
            );

            // Assertions
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody().size()).isEqualTo(1);
        }

    }




