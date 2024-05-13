package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
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

    public EmployeEntity getEmployeByEmail(String emailEmploye) throws NotFoundEmployeEntityException {
        if (emailEmploye != null) {
            EmployeEntity employe = employeRepository.findByEmail(emailEmploye);
            if (employe != null) {
                return employe;
            } else {
                throw new NotFoundEmployeEntityException("Aucun employé trouvé avec l'email : " + emailEmploye);
            }
        } else {
            throw new NotFoundEmployeEntityException("Email invalide");
        }
    }

}
