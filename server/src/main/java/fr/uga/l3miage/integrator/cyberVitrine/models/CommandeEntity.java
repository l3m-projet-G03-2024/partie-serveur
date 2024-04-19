package fr.uga.l3miage.integrator.cyberVitrine.models;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "commande")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeEntity {
    @Id
    @Column(name = "reference_commande")
    private String reference ;

    private EtatsDeCommande etat ;

    @Column(name = "date_creation")
    private LocalDateTime dateDeCreation ;

    @Column(nullable = true)
    private Integer note ;

    @Column(nullable = true)
    private String commentaire ;

    @ManyToOne
    private ClientEntity clientEntity ;

    @OneToMany(mappedBy = "commandeEntity")
    private Set<LigneEntity> ligneEntities ;

    @ManyToOne(optional = true)
    private LivraisonEntity livraisonEntity;

}
