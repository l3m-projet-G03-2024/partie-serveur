package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;

import java.util.List;

@Repository
public interface LivraisonRepository extends JpaRepository<LivraisonEntity,String> {

    List<LivraisonEntity> findAllByEtat(EtatsDeLivraison etat);

}
