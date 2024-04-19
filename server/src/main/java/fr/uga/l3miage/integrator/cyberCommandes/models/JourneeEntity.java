package fr.uga.l3miage.integrator.cyberCommandes.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "journee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JourneeEntity {

    @Id
    @Column(name = "reference_journee")
    private String reference;

    private EtatsDeJournee etat;
    
    private Date date;

    @Column(name = "distance_a_parcourir", nullable = true)
    private Double distanceAParcourir;

    @Column(nullable = true)
    private Double montant;

    @Column(nullable = true)
    private Integer tdmTheorique;

    @OneToMany(mappedBy = "journee")
    private Set<TourneeEntity> tournees;

    @ManyToOne
    private EntrepotEntity entrepotEntity;
    
}
