package fr.uga.l3miage.integrator.cyberProduit.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockEntity {
    @Id
    @Column(name = "id_stock")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stock;
    private Integer quantite;
}
