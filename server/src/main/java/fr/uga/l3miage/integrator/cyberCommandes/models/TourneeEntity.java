package fr.uga.l3miage.integrator.cyberCommandes.models;

import java.util.Set;

import javax.persistence.*;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import lombok.*;

@Entity
@Table(name = "tournee")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourneeEntity {

    @Id
    @Column(name = "reference_tournee")
    private String reference;

    private EtatsDeTournee etat;

    private Integer tdrTheorique;

    private Integer tdrEffectif;

    @Column(nullable =  true)
    private Double distance;

    @OneToMany(mappedBy = "tourneeEntity")
    private Set<LivraisonEntity> livraisons;

    @ManyToMany(mappedBy = "tourneeEntities")
    private Set<EmployeEntity> employeEntities;

    @ManyToOne
    private JourneeEntity journee;

    @ManyToOne
    private CamionEntity camion;

}
