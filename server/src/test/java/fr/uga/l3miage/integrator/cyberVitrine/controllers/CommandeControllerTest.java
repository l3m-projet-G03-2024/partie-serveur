package fr.uga.l3miage.integrator.cyberVitrine.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.services.CommandeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.lang.reflect.Type;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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

    @MockBean
    private CommandeService commandeService ;

    @BeforeEach
    public void setup() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }


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
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/commandes/",String.class);

        // Then
       // assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("[]");

    }

    @Test
    void updateCommandesSuccess() {
        //given
        final HttpHeaders headers = new HttpHeaders();

        CommandeEntity commandeEntity1 = CommandeEntity.builder()
                .reference("REF1")
                .etat(EtatsDeCommande.OUVERTE).build();
        commandeRepository.save(commandeEntity1) ;

        CommandeEntity commandeEntity2 = CommandeEntity.builder()
                .reference("REF2")
                .etat(EtatsDeCommande.PLANIFIEE).build();
        commandeRepository.save(commandeEntity2) ;

        final CommandeUpdatingRequest commande1 = CommandeUpdatingRequest
                .builder()
                .reference("REF1")
                .etat(EtatsDeCommande.ENLIVRAISON)
                .referenceLivraison("REF_LIVRAISON_1")
                .build();

        final CommandeUpdatingRequest commande2 = CommandeUpdatingRequest
                .builder()
                .reference("REF2")
                .etat(EtatsDeCommande.LIVREE)
                .referenceLivraison("REF_LIVRAISON_2")
                .build();

        List<CommandeUpdatingRequest> commandes = new ArrayList<>();
        commandes.add(commande1);
        commandes.add(commande2);

        // when
        ResponseEntity<List<CommandeResponseDTO>> responseEntities = testRestTemplate.exchange(
                "/api/v1/commandes/",
                HttpMethod.PATCH,
                new HttpEntity<>(commandes, headers),
                new ParameterizedTypeReference<List<CommandeResponseDTO>>() {}
        ) ;

        // then
        assertThat(responseEntities.getStatusCode()).isEqualTo(HttpStatus.OK) ;
    }

    @Test
    void updateCommandesFailure() {
        //given
        final HttpHeaders headers = new HttpHeaders();

        CommandeEntity commandeEntity1 = CommandeEntity.builder()
                .reference("REF1")
                .etat(EtatsDeCommande.OUVERTE).build();
        commandeRepository.save(commandeEntity1) ;

        CommandeEntity commandeEntity2 = CommandeEntity.builder()
                .reference("REF2")
                .etat(EtatsDeCommande.PLANIFIEE).build();
        commandeRepository.save(commandeEntity2) ;

        final CommandeUpdatingRequest commande1 = CommandeUpdatingRequest
                .builder()
                .reference("REF1 non existante")
                .etat(EtatsDeCommande.ENLIVRAISON)
                .referenceLivraison("REF_LIVRAISON_1")
                .build();

        final CommandeUpdatingRequest commande2 = CommandeUpdatingRequest
                .builder()
                .reference("REF2 non existante")
                .etat(EtatsDeCommande.LIVREE)
                .referenceLivraison("REF_LIVRAISON_2")
                .build();

        List<CommandeUpdatingRequest> commandes = new ArrayList<>();
        commandes.add(commande1);
        commandes.add(commande2);

        // when
        ResponseEntity<BadRequestRestException> response = testRestTemplate.exchange(
                "/api/v1/commandes/",
                HttpMethod.PATCH,
                new HttpEntity<>(null, headers),
                BadRequestRestException.class
        ) ;

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST) ;
    }

}
