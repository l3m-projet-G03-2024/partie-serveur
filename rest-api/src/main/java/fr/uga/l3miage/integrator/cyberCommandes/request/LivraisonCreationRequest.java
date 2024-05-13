package fr.uga.l3miage.integrator.cyberCommandes.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;



@Data
@Builder
public class LivraisonCreationRequest {
    @Schema(description = "reference d'une livraison")
    private final String referenceCommande;
    @Schema(description = "date de livraison")
    private final String dateLivraison;
}
