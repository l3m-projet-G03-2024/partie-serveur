package fr.uga.l3miage.integrator.cyberRessources.repositories;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEntrepotEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;



@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CamionRepositoryTest {
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;

    @Test
    void getCamionsOK(){
        // Créer un entrpot
        EntrepotEntity entrepotEntity = EntrepotEntity.builder()
                .nom("E1").build();
        entrepotRepository.save(entrepotEntity);

        // CamionEntity est dans entrepotEntity
        CamionEntity camionEntity = CamionEntity.builder()
                .immatriculation("A100")
                .longitude(1.9)
                .latitude(2.0)
                .entrepot(entrepotEntity)
                .build();

        camionRepository.save(camionEntity);

        List<CamionEntity> camions = camionRepository.findByEntrepotNom("E1");


        //then
        assertThat(camions).hasSize(1);
        assertThat(camions.get(0).getEntrepot().getNom()).isEqualTo("E1");
    }
}
