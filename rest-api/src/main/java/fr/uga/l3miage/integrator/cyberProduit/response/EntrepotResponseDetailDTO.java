package fr.uga.l3miage.integrator.cyberProduit.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EntrepotResponseDetailDTO {
    private String nom;

    private String lettre;

    private String adresse ;

    private String codePostal ;

    private String ville ;

    private Double latitude ;

    private Double longitude ;

}
