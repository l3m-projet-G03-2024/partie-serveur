package fr.uga.l3miage.integrator.cyberVitrine.components;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.errors.technical.CommandeEntityNotFoundException;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CommandeComponentTest {

    @Autowired
    private CommandeComponent commandeComponent;

    @MockBean
    private CommandeRepository commandeRepository;


    @Test
    void findAllCommandesNotFound() {

        // Given
        when(commandeRepository.findAll()).thenReturn(Collections.emptyList());

        // then-wen
        List<CommandeEntity> result = commandeComponent.findAllCommandes();
        assertTrue(result.isEmpty(), "La liste des commandes doit être vide");

    }

    @Test
    void findAllCommandesFound() {
        // Given
        ClientEntity client1 = new ClientEntity();
        ClientEntity client2 = new ClientEntity();
        CommandeEntity commande1 = new CommandeEntity();
        CommandeEntity commande2 = new CommandeEntity();
        commande1.setClientEntity(client1);
        commande2.setClientEntity(client2);
        List<CommandeEntity> commandes = Arrays.asList(commande1,commande2);
        when(commandeRepository.findAll()).thenReturn(commandes);

        // when
        List<CommandeEntity> result = commandeComponent.findAllCommandes();

        // then
        assertFalse(result.isEmpty());
        assertEquals(2,result.size());
        assertEquals(commandes,result);
        assertEquals(client1,result.get(0).getClientEntity());
        assertEquals(client2,result.get(1).getClientEntity());

    }

    @Test
    void findCommandeByEtatNotFound() {
        // Given
        when(commandeRepository.findAllByEtat(EtatsDeCommande.NOTEE))
                .thenReturn(Collections.emptyList());

        // then-wen

        List<CommandeEntity> result = commandeComponent.findCommandByEtat(EtatsDeCommande.NOTEE);
        assertTrue(result.isEmpty(),"la liste des commandes doit être vide");
    }


    @Test
    void findCommandeByEtatFound() {
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
        when(commandeRepository.findAllByEtat(EtatsDeCommande.PLANIFIEE)).thenReturn(commandes);


        // when
        List<CommandeEntity> result = commandeComponent.findCommandByEtat(EtatsDeCommande.PLANIFIEE);

        // then
        assertFalse(result.isEmpty(),"La liste des commandes ne doit pas être vide");
        assertEquals(2,result.size());
        assertEquals(commandes,result);
        assertEquals(commandes.get(0).getEtat(),EtatsDeCommande.PLANIFIEE);
        assertEquals(commandes.get(1).getEtat(),EtatsDeCommande.PLANIFIEE);

    }

    @Test
    void getCommandeByReferenceNotFound(){
        // Given
        when(commandeRepository.findById(anyString())).thenReturn(Optional.empty()) ;

        // then - when
        assertThrows(CommandeEntityNotFoundException.class, () -> commandeComponent.getCommandeByReference("test")) ;
    }

    @Test
    void getCommandeByReferenceFound() {
        // Given
        CommandeEntity commandeEntity = CommandeEntity
                .builder()
                .reference("c123")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();
        when(commandeRepository.findById(anyString())).thenReturn(Optional.of(commandeEntity)) ;

        // when - then
        assertDoesNotThrow(() -> commandeComponent.getCommandeByReference("test"));
    }


}
