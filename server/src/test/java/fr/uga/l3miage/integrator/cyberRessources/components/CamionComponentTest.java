package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEntrepotEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CamionComponentTest {
    @Autowired
    private CamionComponent camionComponent;
    @MockBean
    private CamionRepository camionRepository;
    @MockBean
    private EntrepotRepository entrepotRepository;

    @Test
    void getCamionsNotFound() {
        // Mock entrepotRepository to return a specific entrepot
        EntrepotEntity mockedEntrepot = new EntrepotEntity();
        mockedEntrepot.setNom("E1");
        when(entrepotRepository.findById("E1")).thenReturn(Optional.of(mockedEntrepot));

        // Mock camionRepository to return an empty list when queried by entrepot ID
        when(camionRepository.findByEntrepotNom("E1")).thenReturn(Collections.emptyList());

        // Call the method under test
        List<CamionEntity> result = camionComponent.findAllCamionByIdEntrepot("E1");

        // Assert that the result is indeed an empty list
        assertTrue(result.isEmpty(), "La liste des camions doit être vide");
    }

    @Test
    void getCamionsFound(){
        // Créer un entrpot
        EntrepotEntity entrepotEntity = EntrepotEntity.builder()
                .nom("E1").build();

        // CamionEntity est dans entrepotEntity
        CamionEntity camionEntity = CamionEntity.builder()
                .immatriculation("A100")
                .longitude(1.9)
                .latitude(2.0)
                .entrepot(entrepotEntity)
                .build();
        List<CamionEntity> camions = new ArrayList<>();
        camions.add(camionEntity);
        //when
        when(camionRepository.findByEntrepotNom("E1")).thenReturn(camions);

        when(entrepotRepository.findById("E1")).thenReturn(Optional.of(entrepotEntity));

        //then
        List<CamionEntity> result = camionComponent.findAllCamionByIdEntrepot("E1");

        assertEquals(1, result.size(), "La liste des commandes doit contenir un élément");
    }

}
