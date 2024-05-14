package fr.uga.l3miage.integrator.cyberRessources.controllers;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.services.EmployeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@AutoConfigureWebClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class EmployeControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private EmployeRepository employeRepository;

    @MockBean
    private EmployeService employeService;

    @SpyBean
    private EmployeMapper employeMapper;


    private String accessToken;
    private final HttpHeaders headers = new HttpHeaders();



    @BeforeEach
    public void setup() {
        testRestTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        {
            try {
                File file = ResourceUtils.getFile("classpath:accessToken.txt");
                accessToken = new String(Files.readAllBytes(Paths.get(file.getPath())));
                headers.set("AuthorizationTest", "Test "+accessToken);
                // System.out.println(accessToken);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void canListEmployesByEmploi(){
        //Given

        final EmployeResponseDTO employe1 = EmployeResponseDTO
                .builder()
                .trigramme("test1")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .build();

        final EmployeResponseDTO employe2 = EmployeResponseDTO
                .builder()
                .trigramme("test2")
                .email("test2@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.PRODEUR)
                .build();
        final Set<EmployeResponseDTO> employeResponseDTOS = new HashSet<>();
        employeResponseDTOS.add(employe1);
        employeResponseDTOS.add(employe2);
        employeRepository.saveAll(employeResponseDTOS.stream().map(employeMapper::toEmployeEntity).collect(Collectors.toSet()));

        ResponseEntity<Set<EmployeResponseDTO>> request = testRestTemplate
                .exchange("/api/v1/employes/?emploi=LIVREUR",
                        HttpMethod.GET,
                        new HttpEntity<>(null, headers),
                        new ParameterizedTypeReference<Set<EmployeResponseDTO>>() {
                        });

        //Then
        assertThat(request.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeRepository.count()).isEqualTo(2);
    }
}
