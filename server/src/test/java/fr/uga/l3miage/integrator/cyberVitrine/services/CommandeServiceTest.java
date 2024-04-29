package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.response.ClientDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CommandeServiceTest {

    @Autowired
    private CommandeService commandeService;
    @MockBean
    private CommandeComponent commandeComponent;
    @SpyBean
    private CommandeMapper commandeMapper;


    @Test
    void getCommandesWithNoState() {

       // Given


        ClientEntity client1 = new ClientEntity();
        ClientEntity client2 = new ClientEntity();


        CommandeEntity commande1 = new CommandeEntity(
                "1ABC",
                null,
                null,
                10,
                "magnifique",
                client1,
                null,
                null
        );
        CommandeEntity commande2 = new CommandeEntity(
                "1BC",
                null,
                null,
                9,
                "excellent",
                client2,
                null,
                null

        );


        ClientDetailResponseDTO clientDetailResponseDTO1 = new ClientDetailResponseDTO();
        ClientDetailResponseDTO clientDetailResponseDTO2 = new ClientDetailResponseDTO();



        List<CommandeEntity> commandes = Arrays.asList(commande1,commande2);

        // Configurations et mocks
        when(commandeComponent.findAllCommandes()).thenReturn(commandes);
        when(commandeMapper.toCommandesResponseDTO(commandes)).thenReturn(Arrays.asList(
               new CommandeResponseDTO("1ABC",null,null,clientDetailResponseDTO1),
                new CommandeResponseDTO("1BC",null,null,clientDetailResponseDTO2)
        ));

        // Exécution
        List<CommandeResponseDTO> result = commandeService.getCommandes(null);

        // Vérifications
        assertNotNull(result, "La liste de résultats ne doit pas être nulle");
        assertEquals(2, result.size(), "La liste doit contenir deux éléments");
        assertEquals("1ABC", result.get(0).getReference(), "La référence de la première commande doit correspondre");
        assertEquals("1BC", result.get(1).getReference(), "La référence de la deuxième commande doit correspondre");


    }

    @Test
    void getCommandeWithSpecificState() {
        EtatsDeCommande etatsDeCommande = EtatsDeCommande.LIVREE;

        CommandeEntity commande = new CommandeEntity(
                "1ABDR",
                etatsDeCommande,
                null,
                6,
                null,
                null,
                null,
                null

        );

        commande.setEtat(etatsDeCommande);

        List<CommandeEntity> commandes = List.of(commande);

        when(commandeComponent.findCommandByEtat(etatsDeCommande)).thenReturn(commandes);

        when(commandeMapper.toCommandesResponseDTO(commandes)).thenReturn(List.of(
                new CommandeResponseDTO("1ABDR", etatsDeCommande, null, null)
        ));

        List<CommandeResponseDTO> result = commandeService.getCommandes(etatsDeCommande);

        assertEquals(1, result.size(), "devrait renvoyer une commande");
        verify(commandeComponent).findCommandByEtat(etatsDeCommande);
        assertEquals("1ABDR",result.get(0).getReference());
        verify(commandeMapper,times(2)).toCommandesResponseDTO(commandes);



    }






}
