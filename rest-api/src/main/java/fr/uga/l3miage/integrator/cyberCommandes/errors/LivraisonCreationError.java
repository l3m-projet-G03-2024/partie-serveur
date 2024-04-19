package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
public class LivraisonCreationError {

    @Schema(description = "end point call", example = "/api/livraison/")
    private final String uri;

    @Schema(description = "error message", example = "La commande n°1 n'existe pas")
    private final String errorMessage;

}
