package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LivraisonRepositoryTest {
    @Autowired
    private LivraisonComponent livraisonComponent;
    @MockBean
    private LivraisonRepository livraisonRepository;

    @Test
    void getAllLivraisonNotFound() {

        // Given
        when(livraisonRepository.findAll()).thenReturn(Collections.emptyList());

        // then-when
        List<LivraisonEntity> result = livraisonComponent.findAllLivraisons();
        assertTrue(result.isEmpty(), "La liste des commandes doit être vide");

    }

    @Test
    void getAllLivraisonFound() {

        // Given
        LivraisonEntity livraison1 = new LivraisonEntity();
        LivraisonEntity livraison2 = new LivraisonEntity();
        livraison1.setEtat(EtatsDeLivraison.PLANIFIEE);
        livraison2.setEtat(EtatsDeLivraison.PLANIFIEE);

        List<LivraisonEntity> livraisons = List.of(livraison1, livraison2);
        when(livraisonRepository.findAll()).thenReturn(livraisons);
        when(livraisonRepository.findAllByEtat(EtatsDeLivraison.PLANIFIEE)).thenReturn(livraisons);

        // when
        List<LivraisonEntity> result = livraisonComponent.findAllLivraisons();
        List<LivraisonEntity> result2 = livraisonComponent.findLivraisonByEtat(EtatsDeLivraison.PLANIFIEE);

        // then
        assertEquals(2, result.size());
    }

}