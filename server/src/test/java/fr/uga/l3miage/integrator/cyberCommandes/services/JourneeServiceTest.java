package fr.uga.l3miage.integrator.cyberCommandes.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class JourneeServiceTest {
    @Autowired
    private JourneeService journeeService;
    @MockBean
    private JourneeComponent journeeComponent;
    @SpyBean
    private JourneeMapper journeeMapper;

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

    // Test de recuperation de toutes les journées
    @Test
    void RequestGetAllJournee(){
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
        Set<JourneeEntity> journeeEntities = new HashSet<>();
        journeeEntities.add(journeeEntity);
        journeeEntities.add(journeeEntity2);
        // Configuration de la Mock pour retourner la liste simulée l'orsque la methode findAllJournee est appelé
        when(journeeComponent.findAllJournees()).thenReturn(journeeEntities);

        Set<JourneeDetailResponseDTO> journeeResponseDTOs = journeeService.findAllJournees();
        // Verification du resultat
        assertNotNull(journeeResponseDTOs);
        assertEquals(2, journeeEntities.size());
    }

    /*
        Teste de la creation d'une journée
     */
    @Test
    void createJournee(){
        //Given
        JourneeCreationRequest journeeRequest = JourneeCreationRequest
                .builder()
                .reference("test")
                .date(LocalDate.of(2024, 04, 29))
                .build();

        JourneeEntity journeeEntity = journeeMapper.toEntity(journeeRequest);

        //When
        when(journeeComponent.createJourneeEntity(any(JourneeEntity.class))).thenReturn(journeeEntity);
        JourneeDetailResponseDTO responseExpected = journeeMapper.toJourneeDetailResponseDTO(journeeEntity);
        JourneeDetailResponseDTO response = journeeService.createJournee(journeeRequest);

        //Then
        assertThat(response).usingRecursiveComparison().isEqualTo(responseExpected);
    }

    /*
        Test delete une journée
     */
    @Test
    void deleteJourneeByIdWhenJourneeExist() throws JourneeNotFoundException {
        // Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j1")
                .etat(EtatsDeJournee.NONPLANIFIEE)
                .date(LocalDate.of(2024,04 , 26))
                .build();

        when(journeeComponent.getJourneeById(journeeEntity.getReference())).thenReturn(journeeEntity);

        // When
        journeeService.deleteJourneeById(journeeEntity.getReference());

        // Then
        when(journeeComponent.getJourneeById(journeeEntity.getReference())).thenThrow(new NotFoundEntityRestException("Entity not found"));
        assertThrows(NotFoundEntityRestException.class, () -> journeeComponent.getJourneeById(journeeEntity.getReference()));
    }

    /*
        Test Update Journée
     */
    @Test
    void updateJourneeWhenJourneeExists() throws JourneeNotFoundException {
        // Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j1")
                .etat(EtatsDeJournee.NONPLANIFIEE)
                .date(LocalDate.of(2024, 04, 26))
                .build();
        JourneeUpdateRequest journeeUpdate = JourneeUpdateRequest
                .builder()
                .etat(EtatsDeJournee.PLANIFIEE)
                .date(LocalDate.of(2024, 04, 27))
                .build();

        JourneeEntity updatedJourneeEntity = JourneeEntity
                .builder()
                .reference("j1")
                .etat(journeeUpdate.getEtat())
                .date(journeeUpdate.getDate())
                .build();

        JourneeDetailResponseDTO journeeResponse = journeeMapper.toJourneeDetailResponseDTO(updatedJourneeEntity);

        when(journeeComponent.getJourneeById(journeeEntity.getReference())).thenReturn(journeeEntity);
        when(journeeComponent.updateJournee(journeeEntity)).thenReturn(updatedJourneeEntity);
        when(journeeMapper.toJourneeDetailResponseDTO(updatedJourneeEntity)).thenReturn(journeeResponse);

        // When
        JourneeDetailResponseDTO result = journeeService.updateJournee(journeeEntity.getReference(), journeeUpdate);

        // Then
        assertEquals(journeeResponse, result);
        verify(journeeMapper).updateJourneeFromDTO(journeeUpdate, journeeEntity);
    }

}
