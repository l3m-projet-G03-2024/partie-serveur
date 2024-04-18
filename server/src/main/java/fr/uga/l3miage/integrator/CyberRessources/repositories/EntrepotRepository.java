package fr.uga.l3miage.integrator.CyberRessources.repositories;

import fr.uga.l3miage.integrator.CyberRessources.models.EntrepotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepotRepository extends JpaRepository<EntrepotEntity,String> {
}
