package fr.uga.l3miage.integrator.cyberProduit.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Stock")
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
