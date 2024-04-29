package fr.uga.l3miage.integrator.cyberProduit.models;

import fr.uga.l3miage.integrator.cyberVitrine.models.LigneEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import fr.uga.l3miage.integrator.cyberProduit.enums.Encombrement;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "produit")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
    @Id
    @Column(name = "reference_produit")
    private String reference;
    private String titre;
    @Column(length = 1000)
    private String description;
    private Double prix;
    @Column(name = "option_montage", nullable = true)
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
