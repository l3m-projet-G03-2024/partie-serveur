package fr.uga.l3miage.integrator.CyberRessources.repositories;

import fr.uga.l3miage.integrator.CyberRessources.models.HealthcheckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthcheckRepository extends JpaRepository<HealthcheckEntity,Long> {
}
