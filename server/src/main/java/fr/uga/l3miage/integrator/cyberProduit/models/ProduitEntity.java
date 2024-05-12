package fr.uga.l3miage.integrator.cyberProduit.models;

import fr.uga.l3miage.integrator.cyberVitrine.models.LigneEntity;
import lombok.*;

import fr.uga.l3miage.integrator.cyberProduit.enums.Encombrement;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "produit")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProduitEntity {
    @Id
    private String reference;

    private String titre;

    @Column(length = 1000)
    private String description;

    private Double prix;

    @Column(nullable = true)
    private Boolean aOptionMontage;

    @Column(nullable = true)
    private Integer tdmTheorique;

    @Enumerated(EnumType.STRING)
    private Encombrement encombrement;

    @OneToMany(mappedBy = "produitEntity")
    private Set<LigneEntity> ligneEntities;

    @OneToMany(mappedBy = "produitEntity")
    private Set<StockEntity> stockEntities;

    @ManyToOne
    private CatalogueEntity catalogueEntity;
}
