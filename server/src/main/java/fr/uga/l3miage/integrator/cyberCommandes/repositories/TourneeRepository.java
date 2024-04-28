package fr.uga.l3miage.integrator.cyberCommandes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;

@Repository
public interface TourneeRepository extends JpaRepository<TourneeEntity, String> {
    List<TourneeEntity> findAllByEtatOrJourneeReference(EtatsDeTournee etatsDeTournee,String referenceJournee);
    //List<TourneeEntity> findAllByEtatOrReference(EtatsDeTournee etatsDeTournee,String refJournee);

}