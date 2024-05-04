package fr.uga.l3miage.integrator.cyberRessources.services;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberRessources.components.EmployeComponent;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeServiceTest {
    @Autowired
    private EmployeService employeService;
    @MockBean
    private EmployeComponent employeComponent;
    @Test
    void listEmployesByEmploi(){

        //Given
        EntrepotEntity entrepot = EntrepotEntity
                .builder()
                .nom("GRENIS")
                .build();

        EmployeEntity employe1 = EmployeEntity
                .builder()
                .trigramme("test1")
                .email("test1@gmail.com")
                .nom("nom")
                .prenom("prenom")
                .entrepot(entrepot)
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
                .entrepot(entrepot)
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

        Set<EmployeEntity> employeEntities = new HashSet<>();
        employeEntities.add(employe1);
        employeEntities.add(employe2);
        employeEntities.add(employe3);

        when(employeComponent.findAllEmployes()).thenReturn(employeEntities);
        when(employeComponent.findEmployeByEmploiOrEntrepotNom(Emploi.LIVREUR,null)).thenReturn(Set.of(employe1, employe3));
        when(employeComponent.findEmployeByEmploiOrEntrepotNom(null,"GRENIS")).thenReturn(Set.of(employe1, employe2));
        when(employeComponent.findEmployeByEmploiOrEntrepotNom(Emploi.LIVREUR,"GRENIS")).thenReturn(Set.of(employe1));

        Set<EmployeResponseDTO> employes = employeService.listeEmployeByEmploiOrNomEntrepot(null,null);
        Set<EmployeResponseDTO> livreurs = employeService.listeEmployeByEmploiOrNomEntrepot(Emploi.LIVREUR,null);
        Set<EmployeResponseDTO> employesByEntrepot = employeService.listeEmployeByEmploiOrNomEntrepot(null,"GRENIS");
        Set<EmployeResponseDTO> livreursForGrenis = employeService.listeEmployeByEmploiOrNomEntrepot(Emploi.LIVREUR,"GRENIS");

        assertEquals(3, employes.size());
        assertEquals(2, livreurs.size());
        assertEquals(2, employesByEntrepot.size());
        assertEquals(1, livreursForGrenis.size());
    }
}
