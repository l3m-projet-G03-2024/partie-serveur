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

    public Set<EmployeResponseDTO> listeEmployeByEmploiOrNomEntrepot(Emploi emploi, String nomEntrepot){
        Set<EmployeEntity> employeEntities = null;
        if(emploi != null || nomEntrepot != null ){
            employeEntities = employeComponent.findEmployeByEmploiOrEntrepotNom(emploi,nomEntrepot);
        }
        if(emploi == null && nomEntrepot==null){
           employeEntities = employeComponent.findAllEmployes();
        }

        return employeEntities.stream()
                .map(employeMapper::toEmployeResponseDTO)
                .collect(Collectors.toSet());
    }
}
