package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeServiceTest {
    @Autowired 
    private TourneeService tourneeService;
    @MockBean
    private TourneeComponent tourneeComponent;


    @Test 
    void RequestTourneeByEtat(){
            /*  LivraisonEntity livraisonEntity = LivraisonEntity
                    .builder()
                    .reference("1L")
                    .etat(EtatsDeLivraison.ENDECHARGEMENT)
                    .build();
                LivraisonEntity livraisonEntity2 = LivraisonEntity
                    .builder()
                    .reference("2L")
                    .etat(EtatsDeLivraison.ENCLIENTELE)
                    .build();
                Set<LivraisonEntity> livraisonEntities = new HashSet<>();
                livraisonEntities.add(livraisonEntity);
                livraisonEntities.add(livraisonEntity2);*/
        // Création des entités de tournée simulées
        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
            .reference("1T")
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity2 = TourneeEntity.builder()
            .reference("2T")
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity3 = TourneeEntity.builder()
            .reference("3T")
            .etat(EtatsDeTournee.EFFECTUEE)
            .build();

        // Création d'une liste de tournées simulées
        List<TourneeEntity> tourneeEntities = new ArrayList<>();
        tourneeEntities.add(tourneeEntity1);
        tourneeEntities.add(tourneeEntity2);
        tourneeEntities.add(tourneeEntity3);
        // Configuration du mock pour retourner la liste simulée lorsque findAllTournee est appelé
        when(tourneeComponent.findAllTournee()).thenReturn(tourneeEntities);

        // Configuration du mock pour retourner la liste simulée lorsque findAllTourneesByEtat est appelé avec ENCHARGEMENT
        List<TourneeEntity> tourneesEnChargement = Arrays.asList(tourneeEntity1, tourneeEntity2);
        when(tourneeComponent.findAllTourneesByEtat(EtatsDeTournee.ENCHARGEMENT)).thenReturn(tourneesEnChargement);

        // Appel de la méthode à tester sans spécifier d'état
        List<TourneeResponseDTO> tourneeResponseDTOs = tourneeService.getTourneesByEtats(null);
        assertEquals(3, tourneeResponseDTOs.size()); 
        // Appel de la méthode à tester en spécifiant ENCHARGEMENT comme état
        List<TourneeResponseDTO> tourneeResponseDTOsEnChargement = tourneeService.getTourneesByEtats(EtatsDeTournee.ENCHARGEMENT);
        // On s'attend à recevoir 2 DTO de réponse de tournée en chargement
        assertEquals(2, tourneeResponseDTOsEnChargement.size()); 
    }


}
