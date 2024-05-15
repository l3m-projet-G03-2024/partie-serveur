package fr.uga.l3miage.integrator.cyberProduit.models;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "entrepot")
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

    @Column(nullable = true)
    private String adresse ;

    @Column(nullable = true)
    private String codePostal ;

    @Column(nullable = true)
    private String ville ;

    @Column(nullable = true)
    private Double latitude ;

    @Column(nullable = true)
    private Double longitude ;

    @OneToMany(mappedBy = "entrepotEntity")
    private Set<StockEntity> stockEntities;

    @OneToMany(mappedBy = "entrepot")
    private Set<EmployeEntity> employeEntities;

    @OneToMany(mappedBy = "entrepot")
    private Set<CamionEntity> camionEntities;

    @OneToMany(mappedBy = "entrepotEntity")
    private Set<JourneeEntity> journeeEntities;
}
