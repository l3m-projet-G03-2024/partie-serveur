package fr.uga.l3miage.integrator.cyberCommandes.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    @MockBean
    private EmployeRepository employeRepository;

    private static final Logger logger = LoggerFactory.getLogger(TourneeComponent.class);
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


    @Test
    void addEmployeInTournee() throws TourneeNotFoundException, NotFoundEmployeEntityException {

        EmployeEntity employe = EmployeEntity.builder()
                .trigramme("test1")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .tourneeEntities(new HashSet<>())
                .build();


        TourneeEntity tourneeEntity = TourneeEntity.builder()
                .reference("test")
                .distance(7.00)
                .etat(EtatsDeTournee.PLANIFIEE)
                .tdrEffectif(1)
                .tdrTheorique(1)
                .employeEntities(new HashSet<>())
                .build();
        tourneeEntity.getEmployeEntities().add(employe);
        employe.getTourneeEntities().add(tourneeEntity);


        when(tourneeRepository.findById(any())).thenReturn(Optional.of(tourneeEntity));
        when(employeRepository.findById(any())).thenReturn(Optional.of(employe));

        TourneeEntity tourneeEntity1 = tourneeComponent.addEmployeInTournee("test", "test1");
        Set<EmployeEntity> employeEntities = tourneeEntity1.getEmployeEntities();
        assertNotNull(employeEntities);
        assertEquals(1, employeEntities.size());
    }

}
