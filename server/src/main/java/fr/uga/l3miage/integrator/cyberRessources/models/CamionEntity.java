package fr.uga.l3miage.integrator.cyberRessources.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CamionEntity {
    @Id
    private String immatriculation;

    private Double latitude;

    private Double longitude;
}
