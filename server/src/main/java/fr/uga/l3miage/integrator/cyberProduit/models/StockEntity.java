package fr.uga.l3miage.integrator.cyberProduit.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockEntity {
    @Id
    @Column(name = "id_stock")
    private String stock;
    
    private Integer quantite;

    @ManyToOne
    private ProduitEntity produitEntity;

    @ManyToOne
    private EntrepotEntity entrepotEntity;
}
