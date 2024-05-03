package fr.uga.l3miage.integrator.cyberCommandes.controllers;


import static fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee.NONPLANIFIEE;
import static fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee.PLANIFIEE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.services.JourneeService;
import fr.uga.l3miage.integrator.cyberProduit.components.EntrepotComponent;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.time.LocalDate;
import java.util.Date;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class JourneeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private JourneeRepository journeeRepository;
    @SpyBean
    private JourneeService journeeService;
    @SpyBean
    private EntrepotComponent entrepotComponent;
    @Autowired
    private EntrepotRepository  entrepotRepository;

    @BeforeEach
    public void setup() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    void canCreateJournee(){
        //Given
        final HttpHeaders headers = new HttpHeaders();

        final JourneeCreationRequest journeeCreationRequest = JourneeCreationRequest
                .builder()
                .reference("test")
                .date(LocalDate.of(2024, 04, 29))
                .build();

        //When
        ResponseEntity<JourneeResponseDTO> request = testRestTemplate
                .exchange("/api/v1/journees/",
                        HttpMethod.POST,
                        new HttpEntity<>(journeeCreationRequest, headers),
                        JourneeResponseDTO.class);

        //Then
        assertThat(request.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(journeeRepository.count()).isEqualTo(1);

    }

    @Test
    void updateJourneeSuccess() {
        final HttpHeaders headers = new HttpHeaders();
        EntrepotResponseDetailDTO entrepot =  EntrepotResponseDetailDTO
                .builder()
                .nom("Albis")
                .build();

        EntrepotEntity entrepotEntity = EntrepotEntity
                .builder()
                .nom("Albis")
                .build();
        // Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j258G")
                .etat(NONPLANIFIEE)
                .date(LocalDate.of(2024, 04, 29))
                .distanceAParcourir(123.1)
                .montant(200.0)
                .tdmTheorique(60)
                .build();

        final JourneeUpdateRequest journeeUpdateRequest = JourneeUpdateRequest
                .builder()
                .etat(PLANIFIEE)
                .date(LocalDate.of(2024, 04, 29))
                .distanceAParcourir(123.1)
                .montant(200.0)
                .tdmTheorique(60)
                .nomEntrepot("Albis")
                .build();

        JourneeDetailResponseDTO updatedResponse = JourneeDetailResponseDTO
                .builder()
                .reference("j258G")
                .etat(PLANIFIEE)
                .date(LocalDate.of(2024, 04, 29))
                .distanceAParcourir(123.1)
                .montant(200.0)
                .tdmTheorique(60)
                .entrepot(entrepot)
                .build();
        journeeRepository.save(journeeEntity);
        entrepotRepository.save(entrepotEntity);
       // when(entrepotComponent.getEntrepotByNom("Albis")).thenReturn(entrepotEntity);

        // When
        ResponseEntity<JourneeDetailResponseDTO> response = testRestTemplate.exchange(
                "/api/v1/journees/{referenceJournee}",
                HttpMethod.PATCH,
                new HttpEntity<>(journeeUpdateRequest, headers),
                JourneeDetailResponseDTO.class,updatedResponse.getReference());

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).usingRecursiveComparison().isEqualTo(updatedResponse);
        assertThat(journeeService.getJournee(journeeEntity.getReference()).getEtat()).isEqualTo(updatedResponse.getEtat());
    }

    @Test
    void deleteJourneeSuccess(){
        // Given
        JourneeEntity journeeEntity = JourneeEntity
                .builder()
                .reference("j258G")
                .etat(NONPLANIFIEE)
                .date(LocalDate.of(2024, 04, 29))
                .distanceAParcourir(123.1)
                .montant(200.0)
                .tdmTheorique(60)
                .build();

        journeeRepository.save(journeeEntity);

        // When
        ResponseEntity<Void> response = testRestTemplate.exchange(
                "/api/v1/journees/{referenceJournee}",
                HttpMethod.DELETE,
                null,
                Void.class,
                "j258G");


        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThrows(NotFoundEntityRestException.class, () -> journeeService.getJournee(journeeEntity.getReference()));
    }

}
