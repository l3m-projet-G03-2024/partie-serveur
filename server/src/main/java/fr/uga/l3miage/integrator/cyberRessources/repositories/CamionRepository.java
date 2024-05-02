package fr.uga.l3miage.integrator.cyberRessources.repositories;

import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CamionRepository extends JpaRepository<CamionEntity, String> {
    List<CamionEntity> findByEntrepotNom(String nom);
}
