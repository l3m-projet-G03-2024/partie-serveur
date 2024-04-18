package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;

@Repository
public interface LivraisonRepository extends JpaRepository<LivraisonEntity, String> {
}
