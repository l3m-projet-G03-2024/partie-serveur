package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeComponent {
    private final EmployeRepository employeRepository;
    public Set<EmployeEntity> listEmployesByEmploi(Emploi emploi){
        if(emploi != null){
            return employeRepository.getEmployeEntitiesByEmploi(emploi);
        }else {
            return new HashSet<>(employeRepository.findAll());
        }
    }
}
