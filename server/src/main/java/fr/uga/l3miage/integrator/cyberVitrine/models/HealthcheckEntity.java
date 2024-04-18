package fr.uga.l3miage.integrator.cyberVitrine.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthcheckEntity {
    @Id
    private Long id;
}
