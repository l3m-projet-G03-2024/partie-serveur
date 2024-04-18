package fr.uga.l3miage.integrator.cyberVitrine.models;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatDeCommande;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeEntity {
    @Id
    @Column(name = "reference_commande")
    private String reference ;

    private EtatDeCommande etat ;

    @Column(name = "date_creation")
    private LocalDateTime dateDeCreation ;

    @Column(nullable = true)
    private Integer note ;

    @Column(nullable = true)
    private String commentaire ;

    private Double montantTotal ;

    @ManyToOne
    private ClientEntity clientEntity ;

    @OneToMany(mappedBy = "commandeEntity")
    private Set<LigneEntity> ligneEntities ;

}
