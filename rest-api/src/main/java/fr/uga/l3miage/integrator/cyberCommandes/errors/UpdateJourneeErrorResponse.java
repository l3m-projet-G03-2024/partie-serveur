package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateJourneeErrorResponse {
    @Schema(description = "EndPoint Call",  example = "/api/journees/{referenceJournee}")
    private final String url;
    @Schema(description = "message error", example = "La journée n'a pas été trouvée avec la référence fournie")
    private final String errorMessage;
}
