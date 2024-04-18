package fr.uga.l3miage.integrator.cyberProduit.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CatalogueEntity {
    @Id
    private String libelle;
}
