package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JourneeNotFoundResponse {
    @Schema(description = "reference d'une journee", example = "R1")
    private final String refJournee;
    @Schema(description = "end point call", example = "/api/drone/")
    private final String uri;
    @Schema(description = "error message", example = "journée inexistante")
    private final String errorMessage;
}
