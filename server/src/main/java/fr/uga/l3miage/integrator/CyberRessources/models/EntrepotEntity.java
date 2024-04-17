package fr.uga.l3miage.integrator.CyberRessources.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntrepotEntity {
    @Id
    @Column(name = "nom_entrepot")
    private String nom;
    private String lettre;
}
