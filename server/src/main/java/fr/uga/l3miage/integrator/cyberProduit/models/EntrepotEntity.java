package fr.uga.l3miage.integrator.cyberProduit.models;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Entrepot")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EntrepotEntity {
    @Id
    @Column(name = "nom_entrepot")
    private String nom;

    private String lettre;

    @OneToMany(mappedBy = "entrepotEntity")
    private Set<StockEntity> stockEntities;

    @OneToOne(mappedBy = "entrepot")
    private EmployeEntity employeEntity;

    @OneToMany(mappedBy = "entrepot")
    private Set<CamionEntity> camionEntities;

    @OneToMany(mappedBy = "entrepotEntity")
    private Set<JourneeEntity> journeeEntities;
}
