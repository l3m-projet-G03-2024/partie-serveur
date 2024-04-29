package fr.uga.l3miage.integrator.cyberCommandes.controllers;


import static org.assertj.core.api.Assertions.assertThat;


import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

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
    private JourneeComponent journeeComponent;



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
                .exchange("/api/v1/journees",
                        HttpMethod.POST,
                        new HttpEntity<>(journeeCreationRequest, headers),
                        JourneeResponseDTO.class);

        //Then
        assertThat(request.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(journeeRepository.count()).isEqualTo(1);

    }

}
