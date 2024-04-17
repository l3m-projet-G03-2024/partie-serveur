package fr.uga.l3miage.integrator.CyberRessources.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockEntity {
    @Id
    @Column(name = "id_stock")
    private Integer stock;
    private Integer quantite;
}
