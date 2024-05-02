package fr.uga.l3miage.integrator.cyberRessources.services;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.components.CamionComponent;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CamionServiceTest {
    @Autowired
    private CamionService camionService;
    @MockBean
    private CamionComponent camionComponent;


    @Test
    void getCamionsFound() {

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

        when(camionComponent.findAllCamionByIdEntrepot("E1")).thenReturn(camions);

        List<CamionResponseDTO> resultExpected = camionService.getCamions("E1");

        assertEquals(1,resultExpected.size());

    }


    @Test
    void getCamionsNotFound() {
        // Given
        when(camionComponent.findAllCamionByIdEntrepot(anyString())).thenReturn(Collections.emptyList());

        // When
        List<CamionEntity> result = camionComponent.findAllCamionByIdEntrepot("someId");

        // Then
        assertEquals(0, result.size(), "The list should be empty when no camions are found.");
    }


}
