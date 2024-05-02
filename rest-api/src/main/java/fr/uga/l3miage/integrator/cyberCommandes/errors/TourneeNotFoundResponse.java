package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class TourneeNotFoundResponse {
     @Schema(description = "reference d'une tournee", example = "T001")
    private final String referenceTournee;
    @Schema(description = "end point call", example = "/api/drone/")
    private final String uri;
    @Schema(description = "error message", example = "tournée inexistante")
    private final String errorMessage;

}
