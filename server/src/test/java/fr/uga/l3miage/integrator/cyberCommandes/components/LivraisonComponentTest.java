package fr.uga.l3miage.integrator.cyberCommandes.components;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LivraisonComponentTest {
    @Autowired
    private LivraisonComponent livraisonComponent;

    @MockBean
    private LivraisonRepository livraisonRepository;

    @Test
    void findAllLivraisonsNotFound() {

        //Given
        when(livraisonRepository.findAll()).thenReturn(Collections.emptyList());
        // then-wen
        List<LivraisonEntity> result = livraisonComponent.findAllLivraisons();
        assertTrue(result.isEmpty(), "La liste des commandes doit être vide");

    }

    @Test
    void findAllLivraisonsFound() {
        //Given
        LivraisonEntity livraisonEntity = new LivraisonEntity();
        when(livraisonRepository.findAll()).thenReturn(Collections.singletonList(livraisonEntity));
        // then-wen
        List<LivraisonEntity> result = livraisonComponent.findAllLivraisons();
        assertEquals(1, result.size(), "La liste des commandes doit contenir un élément");
    }

    @Test
    void findLivraisonByEtatNotFound() {
        //Given
        when(livraisonRepository.findAllByEtat(null)).thenReturn(Collections.emptyList());
        // then-wen
        List<LivraisonEntity> result = livraisonComponent.findLivraisonByEtat(null);
        assertTrue(result.isEmpty(), "La liste des commandes doit être vide");

    }

    @Test
    void findLivraisonByEtatFound() {
        //Given
        LivraisonEntity livraisonEntity1 = new LivraisonEntity();
        livraisonEntity1.setEtat(EtatsDeLivraison.PLANIFIEE);


        LivraisonEntity livraisonEntity2 = new LivraisonEntity();
        livraisonEntity2.setEtat(EtatsDeLivraison.ENPARCOURS);

        when(livraisonRepository.findAllByEtat(EtatsDeLivraison.PLANIFIEE))
                .thenReturn(Collections.singletonList(livraisonEntity1));

        when(livraisonRepository.findAllByEtat(EtatsDeLivraison.ENPARCOURS))
                .thenReturn(Collections.singletonList(livraisonEntity2));
        // then-when

        List<LivraisonEntity> result = livraisonComponent.findLivraisonByEtat(EtatsDeLivraison.PLANIFIEE);
        assertEquals(1, result.size(), "La liste des commandes doit contenir un élément");
    }
    @Test
    void canCreateLivraison() {
        // Création d'une entité de livraison
        LivraisonEntity livraisonEntity = LivraisonEntity
                .builder()
                .reference("1L")
                .etat(EtatsDeLivraison.PLANIFIEE)
                .build();
        livraisonRepository.save(livraisonEntity);

        // Configuration du comportement du mock pour la méthode save
        when(livraisonRepository.save(any())).thenReturn(livraisonEntity);

        // Appel de la méthode createLivraisons avec une liste contenant une seule livraison
        List<LivraisonEntity> livraisonEntities = livraisonComponent.createLivraisons(List.of(livraisonEntity));

        // Vérification que la méthode save a bien été appelée une fois avec n'importe quelle entité de livraison
        verify(livraisonRepository, times(1)).save(any(LivraisonEntity.class));
    }

    @Test
    void canUpdateLivraison(){
        //Given
        LivraisonEntity livraison = LivraisonEntity
                .builder()
                .reference("l025")
                .etat(EtatsDeLivraison.PLANIFIEE)
                .ordre(2)
                .tdcEffectif(30)
                .tddEffectif(30)
                .tdpEffectif(30)
                .tecEffectif(30)
                .build();
        when(livraisonRepository.save(livraison)).thenReturn(livraison);

        //When
        livraison.setEtat(EtatsDeLivraison.EFFECTUEE);
        livraison.setTdcEffectif(1);
        livraison.setOrdre(1);
        livraison.setTdcEffectif(30);
        livraison.setTddEffectif(30);
        livraison.setTdpEffectif(30);
        livraison.setTecEffectif(30);
        LivraisonEntity result = livraisonComponent.updateLivraison(livraison);

        //Then
        assertThat(result.getEtat()).isEqualTo(EtatsDeLivraison.EFFECTUEE);
        assertThat(result.getTdcEffectif()).isEqualTo(30);
        assertThat(result.getOrdre()).isEqualTo(1);
        assertThat(result.getTddEffectif()).isEqualTo(30);
        assertThat(result.getTdpEffectif()).isEqualTo(30);
        assertThat(result.getTecEffectif()).isEqualTo(30);

    }


}