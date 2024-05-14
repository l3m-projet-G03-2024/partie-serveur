package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonUpdateResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import fr.uga.l3miage.integrator.cyberVitrine.services.CommandeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LivraisonServiceTest {
    @Autowired
    private LivraisonService livraisonService;

    @MockBean
    private LivraisonComponent livraisonComponent;

    @MockBean
    private TourneeRepository tourneeRepository;

    @SpyBean
    private LivraisonMapper livraisonMapper;

    @Autowired
    private CommandeRepository commandeRepository;
    @Test
    void getLivraisonsWithNoState() {
        // Given: Setup test entities and mocks
        ClientEntity client1 = new ClientEntity();
        CommandeEntity commande1 = new CommandeEntity("1ABC", null, null, 10, "magnifique", client1, null, null);

        LivraisonEntity livraison1 = new LivraisonEntity();
        livraison1.setEtat(null); // No state set
        livraison1.setReference("1ABC");
        livraison1.setCommandes(Set.of(commande1));

        List<LivraisonEntity> livraisons = List.of(livraison1);

        //CommandeLivraisonResponseDTO c1 =new CommandeLivraisonResponseDTO();

        // Configurations et mocks
        when(livraisonComponent.findAllLivraisons()).thenReturn(livraisons);
        when(livraisonMapper.toLivraisonResponse(livraisons)).thenReturn(List.of(
                new LivraisonResponseDTO("1ABC", null, 1, null)
        ));

        // When: Call the method under test
        // List<LivraisonResponseDTO> result = livraisonComponent.findLivraisonByEtat(null);
        List<LivraisonResponseDTO> result = livraisonService.getLivraisons(null);

        // Then: Verify the results
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("1ABC", result.get(0).getReference());
        assertNull(result.get(0).getEtat());


    }

    @Test
    void getLivraisonWithSpecificState() {
        EtatsDeLivraison etatsDeLivraison = EtatsDeLivraison.EFFECTUEE;

        LivraisonEntity livraison = new LivraisonEntity();
        livraison.setReference("1AB");
        livraison.setEtat(etatsDeLivraison);

        List<LivraisonEntity> livraisons = List.of(livraison);

        when(livraisonComponent.findLivraisonByEtat(etatsDeLivraison)).thenReturn(livraisons);

        when(livraisonMapper.toLivraisonResponse(livraisons)).thenReturn(
                List.of(new LivraisonResponseDTO("1AB",etatsDeLivraison,1,null))
        );

        List<LivraisonResponseDTO> result = livraisonService.getLivraisons(etatsDeLivraison);

        assertEquals(1,result.size());
        verify(livraisonComponent).findLivraisonByEtat(etatsDeLivraison);
        assertEquals("1AB",result.get(0).getReference());
        verify(livraisonMapper,times(2)).toLivraisonResponse(livraisons);
    }

    @Test
    void createLivraisonsSucces() {
        TourneeEntity tourneeEntity = TourneeEntity
                .builder()
                .reference("T23")
                .etat(EtatsDeTournee.PLANIFIEE)
                .build();
        tourneeRepository.save(tourneeEntity);
        LivraisonTourneeRequest livraisonTourneeRequest = LivraisonTourneeRequest
                .builder()
                .reference("2L")
                .etat(EtatsDeLivraison.PLANIFIEE)
                .ordre(2)
                .referenceTournee("T23")
                .build();
        LivraisonTourneeRequest livraisonTourneeRequest1 = LivraisonTourneeRequest
                .builder()
                .reference("3L")
                .etat(EtatsDeLivraison.EFFECTUEE)
                .ordre(3)
                .referenceTournee("T23")
                .build();

        LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest  = LivraisonsCreationTourneeRequest
                .builder()
                .livraisons(List.of(livraisonTourneeRequest,livraisonTourneeRequest1))
                .build();
        // Mock de la méthode findTourneeByReference pour renvoyer une tournée existante
        when(tourneeRepository.findById("T23")).thenReturn(Optional.of(tourneeEntity));

        LivraisonCreationResponseDTO response = livraisonService.createLivraisons(livraisonsCreationTourneeRequest);
        assertTrue(response.isSucces());
        assertThat(response.getMessage()).isEqualTo("Livraisons crées avec succès");
    }
    @Test
    void getLivraisonsByCommande() throws LivraisonNotFoundException {
        LivraisonEntity livraison =  LivraisonEntity
                .builder()
                .reference("L07")
                .ordre(4)
                .build();
        when(livraisonComponent.getLivraisonByReference(anyString())).thenReturn(livraison);
        LivraisonResponseDTO expectedResponse = livraisonMapper.toLivraisonResponseDTO(livraison) ;

        // when
        LivraisonResponseDTO actualResponse = livraisonService.getLivraisonsByCommandes(livraison.getReference());

        // then
        assertNotNull(actualResponse);
        assertThat(expectedResponse).usingRecursiveComparison().isEqualTo(actualResponse) ;
        verify(livraisonComponent, times(1)).getLivraisonByReference(anyString());
        verify(livraisonMapper,times(2)).toLivraisonResponseDTO(livraison) ;

    }
    @Test
    void getLivraisonsByCommandeNotFound() throws LivraisonNotFoundException {
        // Given
        when(livraisonComponent.getLivraisonByReference(anyString())).thenThrow(LivraisonNotFoundException.class);
        // when - then

        assertThrows(NotFoundRestException.class,() -> livraisonService.getLivraisonsByCommandes(anyString()));

    }

    @Test
    void updateLivraisonSuccess() throws LivraisonNotFoundException {
        // Given
        String referenceLivraison = "ref123";
        LivraisonUpdateRequest request = LivraisonUpdateRequest
                .builder()
                .etat(EtatsDeLivraison.EFFECTUEE)
                .build();

        CommandeEntity commande1 = CommandeEntity
                .builder()
                .reference("cmd1")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();

        CommandeEntity commande2 = CommandeEntity
                .builder()
                .reference("cmd2")
                .etat(EtatsDeCommande.PLANIFIEE)
                .build();

        Set<CommandeEntity> commandes = new HashSet<>();
        commandes.add(commande1);
        commandes.add(commande2);

        LivraisonEntity existingLivraison = LivraisonEntity
                .builder()
                .reference(referenceLivraison)
                .commandes(commandes)
                .build();

        LivraisonEntity updatedLivraison = LivraisonEntity
                .builder()
                .reference(referenceLivraison)
                .commandes(commandes)
                .build();

        LivraisonUpdateResponseDTO expectedResponse = LivraisonUpdateResponseDTO.builder()
                .reference(referenceLivraison)
                .etat(EtatsDeLivraison.EFFECTUEE)
                .commandes(Set.of())
                .build();

        // When
        when(livraisonComponent.getLivraisonByReference(referenceLivraison)).thenReturn(existingLivraison);
        doNothing().when(livraisonMapper).updateLivraisonFromDTO(request, existingLivraison);
        when(livraisonComponent.updateLivraison(existingLivraison)).thenReturn(updatedLivraison);
        when(livraisonMapper.toLivraisonUpdateResponseDTO(updatedLivraison)).thenReturn(expectedResponse);
        LivraisonUpdateResponseDTO actualResponse = livraisonService.updateLivraison(referenceLivraison, request);

        // Then
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
        assertThat(commande1.getEtat()).isEqualTo(EtatsDeCommande.LIVREE);
        assertThat(commande2.getEtat()).isEqualTo(EtatsDeCommande.LIVREE);
        assertThat(actualResponse.getEtat()).isEqualTo(EtatsDeLivraison.EFFECTUEE);
    }

}