package fr.uga.l3miage.integrator.cyberVitrine.controllers;


import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.cyberProduit.exceptions.rest.CreationFailedRestException;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;


import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingBodyRequest;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;

import fr.uga.l3miage.integrator.cyberVitrine.response.DetailsCommandeResponseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CommandeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LivraisonRepository livraisonRepository;




    private String accessToken;
    private final HttpHeaders headers = new HttpHeaders();



    @BeforeEach
    public void setup() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        {
            try {
                File file = ResourceUtils.getFile("classpath:accessToken.txt");
                accessToken = new String(Files.readAllBytes(Paths.get(file.getPath())));
                headers.set("AuthorizationTest", "Test "+accessToken);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @AfterEach
    public void clear() {
       commandeRepository.deleteAll();
   }



    @Test
    void getCommandesTest() {
        // Given
        final Map<String,Object> urlParams = new HashMap<>();
        urlParams.put("etat","PLANIFIEE");

        CommandeEntity commande1 = CommandeEntity
                .builder()
                .reference("c01")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        CommandeEntity commande2 = CommandeEntity
                .builder()
                .reference("c02")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();

        List<CommandeEntity> commandes = new ArrayList<>();
        commandes.add(commande1);
        commandes.add(commande2);
        commandeRepository.saveAll(commandes);



        // when


        ResponseEntity<List<CommandeResponseDTO>> response = testRestTemplate
                .exchange(
                        "/api/v1/commandes/?etat=PLANIFIEE",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<List<CommandeResponseDTO>>() {},
                        urlParams
                );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<CommandeResponseDTO> commandeResponseDTOS = response.getBody();
        assertThat(commandeResponseDTOS).isNotNull();
        assertEquals(2,commandes.size());

    }

    @Test
    void updateCommandesSuccess() {

        LivraisonEntity livraisonEntity1 = LivraisonEntity
                .builder()
                .reference("REF_LIVRAISON_1")
                .build();

        livraisonRepository.save(livraisonEntity1);

        LivraisonEntity livraisonEntity2 = LivraisonEntity
                .builder()
                .reference("REF_LIVRAISON_2")
                .build();

        livraisonRepository.save(livraisonEntity2);

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
                .referenceLivraison(livraisonEntity1.getReference())
                .build();

        final CommandeUpdatingRequest commande2 = CommandeUpdatingRequest
                .builder()
                .reference("REF2")
                .etat(EtatsDeCommande.LIVREE)
                .referenceLivraison(livraisonEntity2.getReference())
                .build();

        List<CommandeUpdatingRequest> commandes = new ArrayList<>();
        commandes.add(commande1);
        commandes.add(commande2);

        CommandeUpdatingBodyRequest commandeUpdatingBodyRequest = CommandeUpdatingBodyRequest
                .builder()
                .commandes(commandes)
                .build();

        ResponseEntity<List<CommandeResponseDTO>> responseEntities = testRestTemplate.exchange(
                "/api/v1/commandes/",
                HttpMethod.PATCH,
                new HttpEntity<>(commandeUpdatingBodyRequest,headers),
                new ParameterizedTypeReference<List<CommandeResponseDTO>>() {}
        );


        assertThat(responseEntities.getStatusCode()).isEqualTo(HttpStatus.OK) ;
    }

    @Test
    void updateCommandesFailure() {


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




        ResponseEntity<CreationFailedRestException> response = testRestTemplate.exchange(
                "/api/v1/commandes/",
                HttpMethod.PATCH,
                new HttpEntity<>(null, headers),
                CreationFailedRestException.class
        ) ;


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST) ;
    }

    @Test
    void getDetailsCommandeOK() {
        final HttpHeaders headers = new HttpHeaders();
        final Map<String,Object> urlParams = new HashMap<>();
        urlParams.put("referenceCommande","C1");

        CommandeEntity commande = CommandeEntity
                .builder()
                .reference("C1")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        commandeRepository.save(commande);

        ResponseEntity<DetailsCommandeResponseDTO> response = testRestTemplate
                .exchange(
                        "/api/v1/commandes/{referenceCommande}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<DetailsCommandeResponseDTO>() {},
                        urlParams
                );

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DetailsCommandeResponseDTO commandeResponseDTOS = response.getBody();
        assertThat(commandeResponseDTOS).isNotNull();

    }

    @Test
    void getDetailsCommandeBad() {
        final HttpHeaders headers = new HttpHeaders();
        final Map<String,Object> urlParams = new HashMap<>();
        urlParams.put("referenceCommande","C2");

        CommandeEntity commande = CommandeEntity
                .builder()
                .reference("C1")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        commandeRepository.save(commande);

        ResponseEntity<DetailsCommandeResponseDTO> response = testRestTemplate
                .exchange(
                        "/api/v1/commandes/{referenceCommande}",
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        new ParameterizedTypeReference<DetailsCommandeResponseDTO>() {},
                        urlParams
                );
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


}
