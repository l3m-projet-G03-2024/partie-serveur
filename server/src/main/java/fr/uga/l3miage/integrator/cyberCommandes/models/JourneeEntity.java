package fr.uga.l3miage.integrator.cyberCommandes.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatDeJournee;
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

    private EtatDeJournee etatDeJournee;
    
    private Date date;

    private Integer distanceAparcourir;

    private Double montant;

    private Integer tdmTheorique;

    @OneToMany
    private Set<TourneeEntity> tournees;


    
}
