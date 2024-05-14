package fr.uga.l3miage.integrator.cyberCommandes.models;

import javax.persistence.*;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "livraison")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonEntity {
    @Id
    @Column(name= "reference_livraison")
    private String reference;
     
    @Enumerated(EnumType.STRING)
    private EtatsDeLivraison etat;

    private Integer tdpEffectif;

    private Integer tddEffectif;

    private Integer tdcEffectif;

    private Integer tecEffectif;

    private Integer tdmEffectif;

    @Column(nullable =  true)
    private Integer ordre;

    @OneToMany(mappedBy = "livraisonEntity")
    private Set<CommandeEntity> commandes;

    @ManyToOne
    private TourneeEntity tourneeEntity;

}
