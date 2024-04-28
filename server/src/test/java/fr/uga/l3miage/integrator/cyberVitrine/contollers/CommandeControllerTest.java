package fr.uga.l3miage.integrator.cyberVitrine.contollers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.controllers.CommandeController;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CommandeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CommandeRepository commandeRepository;

    @SpyBean
    private CommandeComponent commandeComponent;

    @SpyBean
    private CommandeController commandeController;

   @AfterEach
   public void clear() {
       commandeRepository.deleteAll();
   }

    @Test
    void getAllCommandeNotFound() {


        // Given
        final HttpHeaders headers = new HttpHeaders();
       final Map<String,Object> urlParams = new HashMap<>();
        urlParams.put("commandes","Aucune commande trouvée pour l'état spécifié");


       // when(commandeComponent.findAllCommandes()).thenReturn(Collections.emptyList());

        NotFoundErrorResponse notFoundErrorResponseExpected = NotFoundErrorResponse
                .builder()
                .uri("/api/v1/commandes?etat=MS")
                .errorMessage(null)
                .build();
        // when
        //ResponseEntity<String> response = testRestTemplate.getForEntity("/api/commandes",String.class);
        ResponseEntity<NotFoundErrorResponse> response = testRestTemplate.exchange("/api/v1/commandes/etat=MS",
                HttpMethod.GET,new HttpEntity<>(null,headers),
                NotFoundErrorResponse.class
                );


        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }

    @Test
    void getAllCommandeFound() {
        // Given
        when(commandeComponent.findAllCommandes()).thenReturn(Collections.emptyList());

        // when
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/commandes?etat=PLANIFIEE",String.class);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
    }


}
