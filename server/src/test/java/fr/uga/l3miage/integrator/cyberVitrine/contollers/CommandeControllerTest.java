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
        urlParams.put("commandes","il n'existe pas de commande sans état");


        when(commandeComponent.findAllCommandes()).thenReturn(Collections.emptyList());
        // when
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/commandes?etat",String.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }

    @Test
    void getAllCommandeFound() {
        // Given
        ClientEntity client1 = new ClientEntity();
        ClientEntity client2 = new ClientEntity();
        CommandeEntity commande1 = new CommandeEntity();
        CommandeEntity commande2 = new CommandeEntity();
        commande1.setClientEntity(client1);
        commande2.setClientEntity(client2);


        commande1.setEtat(EtatsDeCommande.PLANIFIEE);
        commande2.setEtat(EtatsDeCommande.PLANIFIEE);

        List<CommandeEntity> commandes = Arrays.asList(commande1,commande2);
        when(commandeRepository.findAll()).thenReturn(commandes);
        when(commandeRepository.findAllByEtat(EtatsDeCommande.PLANIFIEE)).thenReturn(commandes);

        // when
        List<CommandeEntity> result = commandeComponent.findAllCommandes();
        List<CommandeEntity> result2 = commandeComponent.findCommandByEtat(EtatsDeCommande.PLANIFIEE);

        // then
        assertFalse(result.isEmpty());
        assertEquals(2,result.size());
        assertEquals(commandes,result);
        assertEquals(client1,result.get(0).getClientEntity());
        assertEquals(client2,result.get(1).getClientEntity());


        // then
        assertFalse(result2.isEmpty(),"La liste des commandes ne doit pas être vide");
        assertEquals(2,result2.size());
        assertEquals(commandes,result2);
        assertEquals(commandes.get(0).getEtat(),EtatsDeCommande.PLANIFIEE);
        assertEquals(commandes.get(1).getEtat(),EtatsDeCommande.PLANIFIEE);
    }


}
