package fr.uga.l3miage.integrator.cyberVitrine.models;

import fr.uga.l3miage.integrator.cyberProduit.models.ProduitEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligne")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneEntity {
    @Id
    private String id ;

    private Integer quantite ;

    // private Double montant ; à revoir si on va calculer ou pas

    @ManyToOne(optional = false)
    private CommandeEntity commandeEntity ;

    @ManyToOne
    private ProduitEntity produitEntity;
}
