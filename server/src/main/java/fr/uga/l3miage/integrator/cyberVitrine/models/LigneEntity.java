package fr.uga.l3miage.integrator.cyberVitrine.models;

import fr.uga.l3miage.integrator.cyberProduit.models.ProduitEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Ligne")
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

    @ManyToOne(optional = false)
    private CommandeEntity commandeEntity ;

    @ManyToOne
    private ProduitEntity produitEntity;
}
