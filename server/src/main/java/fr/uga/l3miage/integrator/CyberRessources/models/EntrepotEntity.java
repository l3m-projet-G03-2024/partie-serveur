package fr.uga.l3miage.integrator.CyberRessources.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntrepotEntity {
    @Id
    @Column(name = "nom_entrepot")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String nom;
    private String lettre;
}
