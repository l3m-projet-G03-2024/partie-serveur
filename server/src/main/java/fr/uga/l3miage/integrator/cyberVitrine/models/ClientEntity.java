package fr.uga.l3miage.integrator.cyberVitrine.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Client")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    private String email ;

    private String prenom ;

    private String nom ;

    private String adresse ;

    private String codePostal ;

    private String ville ;

    @Column(nullable = true)
    private Double longitude ;

    @Column(nullable = true)
    private Double latitude ;

    @OneToMany(mappedBy = "clientEntity")
    private Set<CommandeEntity> commandeEntities ;
}
