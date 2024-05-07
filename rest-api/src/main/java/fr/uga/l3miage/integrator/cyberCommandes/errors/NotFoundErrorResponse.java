package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundErrorResponse {
    private final String uri;
    private final String errorMessage;
    @Schema(description = "error message", example = "Aucune  Journee  n'existe dans la base de donneé")
    private final String errorMessage2;
    @Schema(description = "error message", example = "Une erreur c'est produit, la journée n'a pas été supprimée")
    private final String errorMessage3;
    @Schema(description = "error message", example = "La livraison n'existe pas")
    private final String errorMessage4;
}
