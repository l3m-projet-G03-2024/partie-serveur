package fr.uga.l3miage.integrator.cyberRessources.controllers;

import fr.uga.l3miage.integrator.cyberRessources.endpoints.EmployeEndPoints;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.services.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class EmployeController implements EmployeEndPoints {
    private final EmployeService employeService;

    @Override
    public Set<EmployeResponseDTO> listEmployeByEmploiOrNomEntrepot(Emploi emploi, String nomEntrepot) {
        return employeService.listeEmployeByEmploiOrNomEntrepot(emploi,nomEntrepot);
    }
}
