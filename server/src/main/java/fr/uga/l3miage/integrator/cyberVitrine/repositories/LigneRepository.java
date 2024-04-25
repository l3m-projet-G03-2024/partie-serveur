package fr.uga.l3miage.integrator.cyberVitrine.repositories;

import fr.uga.l3miage.integrator.cyberVitrine.models.LigneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneRepository extends JpaRepository<LigneEntity, String> {

}
