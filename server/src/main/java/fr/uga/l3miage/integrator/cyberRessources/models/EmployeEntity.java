package fr.uga.l3miage.integrator.cyberRessources.models;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import lombok.*;

import javax.persistence.*;

@Entity
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
}
