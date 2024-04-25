package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeServiceTest {
    @Autowired
    private JourneeService journeeService;
    @MockBean
    private JourneeComponent journeeComponent;
    

    // Test de recuperation d'une journée via son id
    @Test
    void RequestGetJournee() throws JourneeNotFoundException{
        //Création d'une entité journée
        JourneeEntity journeeEntity = JourneeEntity
            .builder()
            .reference("1j")
            .etat(EtatsDeJournee.PLANIFIEE)
            .date(LocalDate.of(2024, 04, 26))
            .build();
        when(journeeComponent.getJourneeById(anyString())).thenReturn(journeeEntity);
        assertEquals("1j", journeeEntity.getReference());
    }
    // Test de recuperation de tous les journées 
    @Test
    void RequestGetAllJournee()throws JourneeNotFoundException{
        // Création de deux journées simulées
        JourneeEntity journeeEntity = JourneeEntity
            .builder()
            .reference("j1")
            .etat(EtatsDeJournee.NONPLANIFIEE)
            .date(LocalDate.of(2024,04 , 26))
            .build();
        JourneeEntity journeeEntity2 = JourneeEntity
            .builder()
            .reference("j2")
            .etat(EtatsDeJournee.NONPLANIFIEE)
            .date(LocalDate.of(2024, 04, 28))
            .build();

        // Création d'une liste de journée simulée
        List<JourneeEntity> journeeEntities = new ArrayList<JourneeEntity>();
        journeeEntities.add(journeeEntity);
        journeeEntities.add(journeeEntity2);
        // Configuration de la Mock pour retourner la liste simulée l'orsque la methode findAllJournee est appelé
        when(journeeComponent.findAllJournees()).thenReturn(journeeEntities);

        List<JourneeResponseDTO> journeeResponseDTOs = journeeService.getAllJournees();
        // Verification du resultat
        assertNotNull(journeeResponseDTOs);
        assertEquals(2, journeeEntities.size());
    }

}
