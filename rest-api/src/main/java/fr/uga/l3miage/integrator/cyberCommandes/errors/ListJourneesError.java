package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ListJourneesError {
    @Schema(description = "endpoint call", example = "/journees")
    private final String uri;
    @Schema(description = "endpoint call", example = "liste des journées non trouvée")
    private final String message;
}
