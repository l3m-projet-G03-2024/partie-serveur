package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
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
    void RequestTourneeByEtatOrReferenceJournee(){

        JourneeEntity journee1 = JourneeEntity.builder()
                .reference("1Ab")
                .build();

        TourneeEntity tourneeEntity1 = TourneeEntity.builder()
            .reference("1T")
                .journee(journee1)
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
        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,null)).thenReturn(tourneesEnChargement);

        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(null,journee1.getReference())).thenReturn(List.of(tourneeEntity1));

        when(tourneeComponent.findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,journee1.getReference())).thenReturn(List.of(tourneeEntity1));

        when(tourneeComponent.findAllTournee()).thenReturn(tourneeEntities);

        // Appel de la méthode à tester en spécifiant seulement l'état
        List<TourneeResponseDTO> tourneeResponseDTOs = tourneeService.getTourneesByEtatsOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,null);
        assertEquals(2, tourneeResponseDTOs.size());

        // Appel de la méthode à tester en spécifiant ENCHARGEMENT comme état et en specifiant la reference de la journée 1
        List<TourneeResponseDTO> tourneeResponseDTOsEnChargement = tourneeService.getTourneesByEtatsOrReferenceJournee(EtatsDeTournee.ENCHARGEMENT,tourneeEntity1.getJournee().getReference());
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(1, tourneeResponseDTOsEnChargement.size());


        // Appel de la méthode à tester en specifiant seulement la reference de la journée 1
        List<TourneeResponseDTO> tourneeResponseDTOsJournee1 = tourneeService.getTourneesByEtatsOrReferenceJournee(null,tourneeEntity1.getJournee().getReference());
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(1, tourneeResponseDTOsJournee1.size());


        // Appel de la méthode à tester
        List<TourneeResponseDTO> tourneeResponseDTOsAll = tourneeService.getTourneesByEtatsOrReferenceJournee(null,null);
        // On s'attend à recevoir 1 DTO de réponse de tournée en chargement
        assertEquals(3, tourneeResponseDTOsAll.size());





    }


}
