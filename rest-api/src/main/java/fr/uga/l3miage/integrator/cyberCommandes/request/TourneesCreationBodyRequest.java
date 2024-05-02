package fr.uga.l3miage.integrator.cyberCommandes.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "requête de création des tournées d'une journée")
public class TourneesCreationBodyRequest {
    @Schema(description = "reference de la journée où on doit créer les tournées")
    private final String referenceJournee;

    @Schema(description = "liste des tournées à créer pour une journée spécifique")
    private final List<TourneeCreationRequest> tournees;
}
