package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeComponentTest {

    @Autowired
    private EmployeComponent employeComponent;

    @MockBean
    private EmployeRepository employeRepository;

    @SpyBean
    private EmployeMapper employeMapper;

    @Test
    void listEmployesByEmploi(){
        //Given
        EmployeEntity employe1 = EmployeEntity
                .builder()
                .trigramme("test1")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .build();

        EmployeEntity employe2 = EmployeEntity
                .builder()
                .trigramme("test2")
                .email("test2@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.PRODEUR)
                .build();

        EmployeEntity employe3 = EmployeEntity
                .builder()
                .trigramme("test3")
                .email("test@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .telephone("1234567890")
                .emploi(Emploi.LIVREUR)
                .build();
        //When (Emploi)
        when(employeRepository.getEmployeEntitiesByEmploi(Emploi.LIVREUR)).thenReturn(Set.of(employe1, employe3));
        Set<EmployeEntity> livreurs = employeComponent.listEmployesByEmploi(Emploi.LIVREUR);
        //Then
        assertEquals(2, livreurs.size());

        //When (Null)
        when(employeRepository.findAll()).thenReturn(List.of(employe1, employe2, employe3));
        Set<EmployeEntity> employeEntities = employeComponent.listEmployesByEmploi(null);
        //Then
        assertEquals(3, employeEntities.size());

    }

    @Test
    void listEmployesEmpty(){
        //Given - When
        when(employeRepository.getEmployeEntitiesByEmploi(any())).thenReturn(Set.of());

        Set<EmployeEntity> employeEntities = employeComponent.listEmployesByEmploi(null);

        //Then
        assertEquals(0, employeEntities.size());
    }
}
