package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.models.ClientEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeLivraisonResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LivraisonServiceTest {
    @Autowired
    private LivraisonService livraisonService;

    @MockBean
    private LivraisonComponent livraisonComponent;

    @SpyBean
    private LivraisonMapper livraisonMapper;
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
    void createLivraisonSuccess() {

    }
}