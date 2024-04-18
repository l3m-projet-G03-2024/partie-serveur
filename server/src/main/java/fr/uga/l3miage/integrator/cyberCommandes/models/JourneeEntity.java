package fr.uga.l3miage.integrator.cyberCommandes.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JourneeEntity {

    @Id
    @Column(name = "reference_journee")
    private String reference;

    private EtatsDeJournee etat;
    
    private Date date;

    @Column(name = "distance_parcourir")
    private Double distanceAParcourir;

    private Double montant;

    private Integer tdmTheorique;

    @OneToMany(mappedBy = "journee")
    private Set<TourneeEntity> tournees;

    @ManyToOne
    private EntrepotEntity entrepotEntity;
    
}
