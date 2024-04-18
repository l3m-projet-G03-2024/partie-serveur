package fr.uga.l3miage.integrator.cyberProduit.repositories;

import fr.uga.l3miage.integrator.cyberProduit.models.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity,Integer> {
}
