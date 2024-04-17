package fr.uga.l3miage.integrator.cyberRessources.repositories;

import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<CamionEntity, String> {
}
