package fr.uga.l3miage.integrator.cyberRessources.services;

import fr.uga.l3miage.integrator.cyberRessources.components.EmployeComponent;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.mappers.EmployeMapper;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeService {
    private final EmployeComponent employeComponent;
    private final EmployeMapper employeMapper;

    public Set<EmployeResponseDTO> listeEmployeByEmploi(Emploi emploi){
        Set<EmployeEntity> employeEntities = employeComponent.listEmployesByEmploi(emploi);
        return employeEntities.stream()
                .map(employeMapper::toResponse)
                .collect(Collectors.toSet());
    }
}
