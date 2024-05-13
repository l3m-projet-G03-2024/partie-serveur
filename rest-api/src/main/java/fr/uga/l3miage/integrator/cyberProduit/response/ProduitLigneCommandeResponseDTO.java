package fr.uga.l3miage.integrator.cyberProduit.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProduitLigneCommandeResponseDTO {
    private String reference;
    private boolean aOptionMontage;
    private String encombrement;
    private Double prix;
    private String titre;
}
