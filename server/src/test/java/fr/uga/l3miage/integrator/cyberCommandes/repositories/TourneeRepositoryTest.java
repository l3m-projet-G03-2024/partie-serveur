package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
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

    @Autowired
    private JourneeRepository journeeRepository;

    @Test
    void testRequestFindAllByEtat(){
        //Création d'un ensemble de tournées
        JourneeEntity journee1 = JourneeEntity.builder().reference("01bc").build();
        TourneeEntity tourneeEntity = TourneeEntity
            .builder()
            .reference("1T")
                .journee(journee1)
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity2 = TourneeEntity
            .builder()
            .reference("2T")
                .journee(journee1)
            .etat(EtatsDeTournee.ENCHARGEMENT)
            .build();
        TourneeEntity tourneeEntity3 = TourneeEntity
            .builder()
            .reference("3T")
            .etat(EtatsDeTournee.EFFECTUEE)
            .build();
        journeeRepository.save(journee1);
        tourneeRepository.save(tourneeEntity);
        tourneeRepository.save(tourneeEntity2);
        tourneeRepository.save(tourneeEntity3);

        List<TourneeEntity> tourneeEntities = tourneeRepository.findAllByEtatOrJourneeReference(EtatsDeTournee.ENCHARGEMENT,journee1.getReference());
        
        // On s'attend à recevoir 2 tournées en chargement
        assertEquals( 2, tourneeEntities.size()); 
    
    }


    
}
