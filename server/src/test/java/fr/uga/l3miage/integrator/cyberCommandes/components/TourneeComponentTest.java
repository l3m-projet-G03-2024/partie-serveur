package fr.uga.l3miage.integrator.cyberCommandes.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TourneeComponentTest {
    @Autowired
    private TourneeComponent tourneeComponent;
    @MockBean
    private TourneeRepository tourneeRepository;

  /*Ce test s'assure que la méthode findAllTournee() fonctionne correctement,
   dans le cas où aucune tournée n'est trouvée dans le repository,
   en retournant une liste vide. */
    @Test
    void findAllTourneeNotFound(){
        // Configurer le mock pour retourner une liste vide
        when(tourneeRepository.findAll()).thenReturn(new ArrayList<>());
        List<TourneeEntity> tourneeEntities = tourneeComponent.findAllTournee();
        //Verification du résultat
        assertTrue(tourneeEntities.isEmpty());
    }
    /*Ce test verifie quant on appel la methode findAllTournee elle nous rammene bien tous les 
     * tournées existantes
     */
    @Test
    void findAllTourneeFound(){
        // Création des tournées
        TourneeEntity tourneeEntity = TourneeEntity
            .builder()
            .reference("1T")
            .distance(7.00)
            .etat(EtatsDeTournee.EFFECTUEE)
            .build();
        TourneeEntity tourneeEntity2 = TourneeEntity
            .builder()
            .reference("2T")
            .distance(11.00)
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity3 = TourneeEntity
            .builder()
            .reference("3T")
            .distance(15.00)
            .etat(EtatsDeTournee.ENMONTAGE)
            .build();
        List<TourneeEntity> tourneeEntities = new ArrayList<>();
        tourneeEntities.add(tourneeEntity);
        tourneeEntities.add(tourneeEntity2);
        tourneeEntities.add(tourneeEntity3);
        //Configuration du mock 
        when(tourneeRepository.findAll()).thenReturn(tourneeEntities);
        //appel de la methode a tester
        List<TourneeEntity> tournees = tourneeComponent.findAllTournee();
        // Vérification du resultat
        assertEquals(tourneeEntities.size(), tournees.size());
        assertEquals(tourneeEntities.size(), 3);



    }

}
