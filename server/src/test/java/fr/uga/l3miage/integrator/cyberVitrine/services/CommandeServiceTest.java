package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberProduit.response.ProduitLigneCommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.errors.technical.CommandeEntityNotFoundException;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingBodyRequest;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.ClientDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.DetailsCommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.LigneCommandeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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



    @MockBean
    private LivraisonComponent livraisonComponent ;


    @Test
    void getCommandesWithNoState() {




        ClientEntity client1 = new ClientEntity();
        ClientEntity client2 = new ClientEntity();




        CommandeEntity commande1 = CommandeEntity
                .builder()
                .reference("1ABC")
                .clientEntity(client1)
                .note(10)
                .commentaire("magnifique")
                .build();

        CommandeEntity commande2 = CommandeEntity
                .builder()
                .reference("1BC")
                .clientEntity(client2)
                .note(9)
                .commentaire("excellent")
                .build();


        ClientDetailResponseDTO clientDetailResponseDTO1 = new ClientDetailResponseDTO();
        ClientDetailResponseDTO clientDetailResponseDTO2 = new ClientDetailResponseDTO();



        List<CommandeEntity> commandes = Arrays.asList(commande1,commande2);


        when(commandeComponent.findAllCommandes()).thenReturn(commandes);
        when(commandeMapper.toCommandesResponseDTO(commandes)).thenReturn(Arrays.asList(
               new CommandeResponseDTO("1ABC",null,null,clientDetailResponseDTO1),
                new CommandeResponseDTO("1BC",null,null,clientDetailResponseDTO2)
        ));


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


        CommandeEntity commande = CommandeEntity
                .builder()
                .reference("1ABDR")
                .etat(etatsDeCommande)
                .note(6)
                .build();

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

//    @Test
//    void testUpdateCommandes() throws CommandeEntityNotFoundException, LivraisonNotFoundException {
//
//
//        CommandeUpdatingRequest commande1 = CommandeUpdatingRequest
//                .builder()
//                .reference("REF1")
//                .etat(EtatsDeCommande.ENLIVRAISON)
//                .referenceLivraison("REF_LIVRAISON_1")
//                .build();
//
//        CommandeUpdatingRequest commande2 = CommandeUpdatingRequest
//                .builder()
//                .reference("REF2")
//                .etat(EtatsDeCommande.LIVREE)
//                .referenceLivraison("REF_LIVRAISON_2")
//                .build();
//
//        List<CommandeUpdatingRequest> commandes = new ArrayList<>();
//        commandes.add(commande1);
//        commandes.add(commande2);
//
//        CommandeEntity commandeEntity1 = new CommandeEntity();
//        commandeEntity1.setReference("REF1");
//
//        CommandeEntity commandeEntity2 = new CommandeEntity();
//        commandeEntity2.setReference("REF2");
//
//        when(commandeComponent.getCommandeByReference("REF1")).thenReturn(commandeEntity1);
//        when(commandeComponent.getCommandeByReference("REF2")).thenReturn(commandeEntity2);
//
//        LivraisonEntity livraisonEntity1 = new LivraisonEntity();
//        LivraisonEntity livraisonEntity2 = new LivraisonEntity();
//
//        when(livraisonComponent.getLivraisonByReference("REF_LIVRAISON_1")).thenReturn(livraisonEntity1);
//        when(livraisonComponent.getLivraisonByReference("REF_LIVRAISON_2")).thenReturn(livraisonEntity2);
//
//        CommandeResponseDTO commandeResponseDTO1 = new CommandeResponseDTO();
//        CommandeResponseDTO commandeResponseDTO2 = new CommandeResponseDTO();
//
//        when(commandeMapper.toCommandeResponseDTO(commandeEntity1)).thenReturn(commandeResponseDTO1);
//        when(commandeMapper.toCommandeResponseDTO(commandeEntity2)).thenReturn(commandeResponseDTO2);
//
//        CommandeUpdatingBodyRequest commandeUpdatingBodyRequest = CommandeUpdatingBodyRequest
//                .builder()
//                .commandes(commandes)
//                .build();
//
//
//        List<CommandeResponseDTO> result = commandeService.updateCommandes(commandeUpdatingBodyRequest);
//
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals(commandeResponseDTO1, result.get(0));
//        assertEquals(commandeResponseDTO2, result.get(1));
//        verify(commandeComponent, times(2)).getCommandeByReference(anyString()) ;
//        verify(livraisonComponent, times(2)).getLivraisonByReference(anyString()) ;
//        assertEquals(commandeComponent.getCommandeByReference("REF1").getEtat(), EtatsDeCommande.ENLIVRAISON);
//        assertEquals(commandeComponent.getCommandeByReference("REF2").getEtat(), EtatsDeCommande.LIVREE);
//    }

    @Test
    void testUpdateCommandes() throws CommandeEntityNotFoundException, LivraisonNotFoundException {

        CommandeUpdatingRequest commande1 = CommandeUpdatingRequest
                .builder()
                .reference("REF1")
                .etat(EtatsDeCommande.ENLIVRAISON)
                .referenceLivraison("REF_LIVRAISON_1")
                .build();

        CommandeUpdatingRequest commande2 = CommandeUpdatingRequest
                .builder()
                .reference("REF2")
                .etat(EtatsDeCommande.LIVREE)
                .referenceLivraison("REF_LIVRAISON_2")
                .build();

        List<CommandeUpdatingRequest> commandes = new ArrayList<>();
        commandes.add(commande1);
        commandes.add(commande2);

        CommandeUpdatingBodyRequest commandeUpdatingBodyRequest = new CommandeUpdatingBodyRequest(commandes) ;

        CommandeEntity commandeEntity1 = new CommandeEntity();
        commandeEntity1.setReference("REF1");

        CommandeEntity commandeEntity2 = new CommandeEntity();
        commandeEntity2.setReference("REF2");

        List<CommandeEntity> commandeEntities = new ArrayList<>() ;
        commandeEntities.add(commandeEntity1) ;
        commandeEntities.add(commandeEntity2) ;

        when(commandeComponent.getCommandeByReference("REF1")).thenReturn(commandeEntity1);
        when(commandeComponent.getCommandeByReference("REF2")).thenReturn(commandeEntity2);

        LivraisonEntity livraisonEntity1 = new LivraisonEntity();
        livraisonEntity1.setReference("REF_LIVRAISON_1");

        LivraisonEntity livraisonEntity2 = new LivraisonEntity();
        livraisonEntity2.setReference("REF_LIVRAISON_2");

        when(livraisonComponent.getLivraisonByReference("REF_LIVRAISON_1")).thenReturn(livraisonEntity1);
        when(livraisonComponent.getLivraisonByReference("REF_LIVRAISON_2")).thenReturn(livraisonEntity2);

        when(commandeComponent.updateCommandes(commandeEntities)).thenReturn(commandeEntities) ;

        CommandeResponseDTO commandeResponseDTO1 = new CommandeResponseDTO();
        CommandeResponseDTO commandeResponseDTO2 = new CommandeResponseDTO();

        List<CommandeResponseDTO> commandeResponseDTOList = new ArrayList<>() ;
        commandeResponseDTOList.add(commandeResponseDTO1) ;
        commandeResponseDTOList.add(commandeResponseDTO2) ;

        when(commandeMapper.toCommandesResponseDTO(commandeEntities)).thenReturn(commandeResponseDTOList) ;

        List<CommandeResponseDTO> result = commandeService.updateCommandes(commandeUpdatingBodyRequest);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(commandeResponseDTO1, result.get(0));
        assertEquals(commandeResponseDTO2, result.get(1));
        verify(commandeComponent, times(2)).getCommandeByReference(anyString()) ;
        verify(livraisonComponent, times(2)).getLivraisonByReference(anyString()) ;
        assertEquals(commandeComponent.getCommandeByReference("REF1").getEtat(), EtatsDeCommande.ENLIVRAISON);
        assertEquals(commandeComponent.getCommandeByReference("REF2").getEtat(), EtatsDeCommande.LIVREE);
    }


    @Test
    void getDetailsCommandeOk() throws CommandeEntityNotFoundException {

        CommandeEntity commandeEntity1 = new CommandeEntity();
        commandeEntity1.setReference("REF1");


        when(commandeComponent.getCommandeByReference("REF1")).thenReturn(commandeEntity1);

        ProduitLigneCommandeResponseDTO produitLigneCommandeResponseDTO = new ProduitLigneCommandeResponseDTO();
        produitLigneCommandeResponseDTO.setReference("p1");

        LigneCommandeResponseDTO ligneCommandeResponseDTO = new LigneCommandeResponseDTO();
        ligneCommandeResponseDTO.setId("L1");
        ligneCommandeResponseDTO.setProduit(produitLigneCommandeResponseDTO);
        Set<LigneCommandeResponseDTO> list = new HashSet<>();
        list.add(ligneCommandeResponseDTO);



        DetailsCommandeResponseDTO detailsCommandeResponseDTO = DetailsCommandeResponseDTO.builder()
                .lignes(list)
                .reference("REF1").build();

        when(commandeService.getDetailsCommande("REF1")).thenReturn(detailsCommandeResponseDTO);


        DetailsCommandeResponseDTO  resultExpected = commandeService.getDetailsCommande("REF1");

        assertNotNull(resultExpected);
        verify(commandeComponent, times(2)).getCommandeByReference(anyString()) ;
        assertThat(detailsCommandeResponseDTO).usingRecursiveComparison().isEqualTo(resultExpected);


    }

    @Test
    void getDetailsCommandeNotFound() throws CommandeEntityNotFoundException {

        CommandeEntity commandeEntity1 = new CommandeEntity();
        commandeEntity1.setReference("REF1");

        when(commandeService.getDetailsCommande("REF2")).thenReturn(null);


        DetailsCommandeResponseDTO  resultExpected = commandeService.getDetailsCommande("REF2");

        assertNull(resultExpected);
        verify(commandeComponent, times(2)).getCommandeByReference(anyString()) ;

    }


}
