package fr.uga.l3miage.integrator.CyberRessources.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthcheckEntity {
    @Id
    private Long id;
}
