package fr.uga.l3miage.integrator.cyberVitrine.repositories;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommandeRepository extends JpaRepository<CommandeEntity, String> {


    List<CommandeEntity> findAllByEtat(EtatsDeCommande etatDeCommande);

}
