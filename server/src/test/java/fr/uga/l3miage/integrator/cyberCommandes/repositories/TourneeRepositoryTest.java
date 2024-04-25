package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class TourneeRepositoryTest {
    @Autowired
    private TourneeRepository tourneeRepository;

    @Test
    void testRequestFindAllByEtat(){
        //Création d'un ensemble de tournées
        TourneeEntity tourneeEntity = TourneeEntity
            .builder()
            .reference("1T")
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity2 = TourneeEntity
            .builder()
            .reference("2T")
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity3 = TourneeEntity
            .builder()
            .reference("3T")
            .etat(EtatsDeTournee.EFFECTUEE)
            .build();
        tourneeRepository.save(tourneeEntity);
        tourneeRepository.save(tourneeEntity2);
        tourneeRepository.save(tourneeEntity3);

        List<TourneeEntity> tourneeEntities = tourneeRepository.findAllByEtat(EtatsDeTournee.ENCHARGEMENT);
        
        // On s'attend à recevoir 2 tournées en chargement
        assertEquals( 2, tourneeEntities.size()); 
    
    }


    
}
