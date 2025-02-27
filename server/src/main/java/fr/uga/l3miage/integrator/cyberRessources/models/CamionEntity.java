package fr.uga.l3miage.integrator.cyberRessources.models;

import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "camion")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CamionEntity {
    @Id
    private String immatriculation;

    private Double latitude;

    private Double longitude;

    @ManyToOne
    private EntrepotEntity entrepot;

    @OneToMany(mappedBy = "camion")
    private Set<TourneeEntity> tourneeEntities;
}
