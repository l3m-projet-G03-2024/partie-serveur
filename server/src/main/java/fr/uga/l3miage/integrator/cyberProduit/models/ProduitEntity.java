package fr.uga.l3miage.integrator.cyberProduit.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import fr.uga.l3miage.integrator.cyberProduit.enums.Encombrement;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
    @Id
    @Column(name = "reference_produit")
    private String reference;
    private String titre;
    private String description;
    private double prix;
    @Column(nullable = true)
    private boolean aOptionMontage;
    @Column(nullable = true)
    private Integer tdmTheorique;
    @Enumerated(EnumType.STRING)
    private Encombrement encombrement;
}
