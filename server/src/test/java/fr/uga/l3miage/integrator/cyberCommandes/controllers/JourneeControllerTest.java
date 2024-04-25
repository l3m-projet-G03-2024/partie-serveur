package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;



import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, properties ="spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
@AutoConfigureWebClient
public class JourneeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired 
    private JourneeRepository journeeRepository;
    @SpyBean
    private JourneeComponent journeeComponent;

    @AfterEach
    void clear(){
        this.journeeRepository.deleteAll();
    }
// Test a corriger
   /*  @Test
    void getJourneeByIdFound(){
        // Given
        try {
            when(journeeComponent.getJourneeById("1J")).thenReturn(new JourneeEntity());
        } catch (JourneeNotFoundException e) {
            // Handle the exception here if needed
        }

        // When
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/tournees/{reference}", String.class, "1J");

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }*/
    /*@Test
    void getNotFoundJournee() {
        // Given
        final HttpHeaders headers = new HttpHeaders();
        final Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("reference", "La journée n'existe pas");
    
        // Mocking the behavior of journeeComponent to return null
        when(journeeComponent.getJourneeById("La journée n'existe pas")).thenReturn(null);
    
        // When
        ResponseEntity<String> response = restTemplate.exchange("/api/v1/journee/{reference}", HttpMethod.GET, new HttpEntity<>(null, headers), String.class, urlParams);
    
        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }*/
    
}
