package fr.uga.l3miage.integrator.cyberCommandes.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeComponentTest {
    @Autowired
    private JourneeComponent journeeComponent;
    @MockBean
    private JourneeRepository journeeRepository;


     /*
      Ce test vérifie que lorsque la méthode getJourneeById de journeeComponent est appelée avec une référence de journée inexistante,
       une JourneeNotFoundException est levée.
      */
    @Test
    void getJourneeByIdNotFound() {
        //GIVEN
        // Configure le mock pour retourner un Optional vide
        //WHEN-THEN
        when(journeeRepository.findById(anyString())).thenReturn(Optional.empty());
        // Vérifie si une JourneeNotFoundException est levée lorsque la méthode getJourneeById est appelée avec une référence inexistante
        assertThrows(JourneeNotFoundException.class, () -> journeeComponent.getJourneeById("1L"));
    }
    /* Ce test vérifie que lorsqu'une journée avec la référence "2L" est trouvée dans le journeeRepository,
     aucune exception n'est levée lors de l'appel de la méthode getJourneeById de journeeComponent */
    @Test
    void getJourneeByIdFound() {
        // Création d'une journée
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("2L")
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(LocalDate.of(2024, 04, 26))
                .build();

        // Configure le mock pour retourner un Optional contenent une instance de journée
        when(journeeRepository.findById(anyString())).thenReturn(Optional.of(journeeEntity));
        // verifie qu'aucune exception n'est levée
        assertDoesNotThrow(() -> journeeComponent.getJourneeById("2L"));

    }


    @Test
    void createJourneeEntity(){
        //Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j028G")
                .etat(EtatsDeJournee.NONPLANIFIEE)
                .build();
        //When
        when(journeeRepository.save(any())).thenReturn(journeeEntity);

        JourneeEntity resultat = journeeComponent.createJourneeEntity(journeeEntity);
        //Then
        assertEquals(resultat, journeeEntity);
        verify(journeeRepository, times(1)).save(any());
    }

    /*
        Teste pour la liste complete des journées
    */
    @Test
    void findAllJourneesMultipleEntries(){
        //Given
        JourneeEntity journee1 = JourneeEntity
                .builder()
                .reference("j028G")
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(LocalDate.of(2024, 04, 26))
                .build();

        JourneeEntity journee2 = JourneeEntity
                .builder()
                .reference("j029G")
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(LocalDate.of(2024, 04, 27))
                .build();
        Set<JourneeEntity> expectedJournees = new HashSet<>();
        expectedJournees.add(journee1);
        expectedJournees.add(journee2);
        when(journeeRepository.findAllBy()).thenReturn(expectedJournees);

        //When
        Set<JourneeEntity> results = journeeComponent.findAllJournees();

        //Then
        assertEquals(expectedJournees.size(), results.size(), "La taille doit être égal à 2");
        assertTrue(results.containsAll(expectedJournees), "Doit contenir toutes les journées");


    }

    @Test
    void findAllJourneesEmpty(){
        //Given
        Set<JourneeEntity> emptyJournees = new HashSet<>();
        when(journeeRepository.findAllBy()).thenReturn(emptyJournees);

        //When
        Set<JourneeEntity> results = journeeComponent.findAllJournees();

        //Then
        assertTrue(results.isEmpty(), "La journée doit être vide");
    }

    /*
      Test de Delete une journée par reference
     */
    @Test
    void deleteJourneeByReferenceWhenExistElement(){
        //Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j021L")
                .date(LocalDate.of(2024, 04, 29))
                .build();

        //When
        journeeRepository.save(journeeEntity);
        journeeComponent.deleteJourneeById(journeeEntity.getReference());

        //Then
        assertFalse(journeeRepository.findById(journeeEntity.getReference()).isPresent());
    }

    /*
        Test update journée
     */
    @Test
    void updateJourneeSuccess() {
        // Given
        JourneeEntity journee1 = JourneeEntity
                .builder()
                .reference("j028G")
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(LocalDate.of(2024, 04, 26))
                .build();
        when(journeeRepository.save(journee1)).thenReturn(journee1);

        // When
        journee1.setEtat(EtatsDeJournee.NONPLANIFIEE);
        journee1.setDate(LocalDate.of(2024, 04, 25));
        JourneeEntity result = journeeComponent.updateJournee(journee1);

        // Then
        assertEquals(journee1.getReference(), result.getReference(), "");
        assertEquals(journee1.getDate(), result.getDate());
        assertEquals(journee1.getEtat(), result.getEtat());
        verify(journeeRepository, times(1)).save(journee1);
    }

}
