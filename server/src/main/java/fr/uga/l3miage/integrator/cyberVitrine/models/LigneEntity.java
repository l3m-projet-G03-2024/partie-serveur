package fr.uga.l3miage.integrator.cyberVitrine.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneEntity {
    @Id
    private String id ;

    private Integer quantite ;

    private Double montant ;

    @ManyToOne
    private CommandeEntity commandeEntity ;
}
