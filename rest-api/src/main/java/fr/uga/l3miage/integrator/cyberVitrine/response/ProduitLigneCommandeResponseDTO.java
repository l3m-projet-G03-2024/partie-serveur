package fr.uga.l3miage.integrator.cyberVitrine.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitLigneCommandeResponseDTO {

    private String reference;

    private String titre;

}
