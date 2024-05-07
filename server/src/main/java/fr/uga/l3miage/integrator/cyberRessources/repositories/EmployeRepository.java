package fr.uga.l3miage.integrator.cyberRessources.repositories;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeEntity, String> {
    Set<EmployeEntity> getEmployeEntitiesByEmploi(Emploi emploi);

    Set<EmployeEntity> findByEmploiOrEntrepotNom(Emploi emploi, String nomEntrepot);
    EmployeEntity getByEmail(String email);
}
