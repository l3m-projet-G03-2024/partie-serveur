package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreationFailedErrorResponse {
    @Schema(description = "end point call", example = "/api/v1/")
    private final String uri;
    @Schema(description = "error message", example = "La tournee n°1 n'existe pas")
    private final String errorMessage;
}
