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

    private LocalDateTime date;

    @Column(nullable = true)
    private Integer tdpTheorique;

    private Integer tdpEffectif;

    @Column(nullable = true)
    private Integer tddTheorique;

    private Integer tddEffectif;

    @Column(nullable = true)
    private Integer tdcTheorique;

    private Integer tdcEffectif;

    @Column(nullable = true)
    private Integer tecTheorique;

    private Integer tecEffectif;

    @Column(nullable = true)
    private Integer tdmTheorique;

    private Integer tdmEffectif;

    @Column(nullable =  true)
    private Integer ordre;

    @OneToMany(mappedBy = "livraisonEntity")
    private Set<CommandeEntity> commandeEntities;

    @ManyToOne
    private TourneeEntity tourneeEntity;

}
