package fr.uga.l3miage.integrator.cyberCommandes.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "requête de création des tournées d'une journée")
public class TourneesCreationBodyRequest {
    @Schema(description = "reference de la journée où on doit créer les tournées")
    private String referenceJournee;

    @Schema(description = "liste des tournées à créer pour une journée spécifique")
    private List<TourneeCreationRequest> tournees;
}
