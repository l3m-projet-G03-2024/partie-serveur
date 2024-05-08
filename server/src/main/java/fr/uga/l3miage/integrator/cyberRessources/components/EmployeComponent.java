package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeComponent {
    private final EmployeRepository employeRepository;

    public Set<EmployeEntity> findEmployeByEmploiOrEntrepotNom(Emploi emploi,String nomEntrepot) {
        return employeRepository.findByEmploiOrEntrepotNom(emploi,nomEntrepot);
    }

    public Set<EmployeEntity> findAllEmployes() {
        return new HashSet<>(employeRepository.findAll());
    }

    public EmployeEntity findEmployeByReference(String reference){
        return employeRepository.findById(reference).orElseThrow();
    }
}
