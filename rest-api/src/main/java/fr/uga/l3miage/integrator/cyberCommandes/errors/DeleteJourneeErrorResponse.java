package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteJourneeErrorResponse {
    @Schema(description = "endpoint call", example = "/{referenceJourner}")
    private final String uri;
    @Schema(description = "Message Error", example = "La journée n'a pas été trouvée avec la référence fournie")
    private final String message;
}
