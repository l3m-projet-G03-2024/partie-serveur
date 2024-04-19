package fr.uga.l3miage.integrator.cyberRessources.models;

import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employe")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeEntity {
    @Id
    private String trigramme;

    private String email;

    private String prenom;

    private String nom;

    private String image;

    @Column(length = 10)
    private String telephone;

    @Enumerated(EnumType.STRING)
    private Emploi emploi;

    @OneToOne(optional = true)
    private EntrepotEntity entrepot;

    @ManyToMany
    private Set<TourneeEntity> tourneeEntities;

}
