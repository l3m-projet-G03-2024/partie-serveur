package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundErrorResponse {
    @Schema(description = "end point call", example = "/api/tram/")
    private final String uri;
    @Schema(description = "error message", example = "La Tournee n°1 n'existe pas")
    private final String errorMessage;
    @Schema(description = "error message", example = "Aucune  Journee  n'existe dans la base de donneé")
    private final String errorMessage2;

}
