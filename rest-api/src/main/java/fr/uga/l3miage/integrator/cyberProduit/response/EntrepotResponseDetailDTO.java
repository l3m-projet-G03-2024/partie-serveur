package fr.uga.l3miage.integrator.cyberProduit.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntrepotResponseDetailDTO {
    private String nom;

    private String lettre;

    private String adresse ;

    private String codePostal ;

    private String ville ;

    private Double latitude ;

    private Double longitude ;

}
