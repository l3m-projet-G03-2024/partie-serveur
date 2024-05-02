package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface JourneeRepository extends JpaRepository<JourneeEntity, String> {
    Set<JourneeEntity> findAllBy();
}

