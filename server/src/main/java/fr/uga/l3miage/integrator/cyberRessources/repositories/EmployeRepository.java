package fr.uga.l3miage.integrator.cyberRessources.repositories;

import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeEntity, String> {
}
