package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TourneeUpdateLivraisonRequest {
    @Schema(description = "Nouvelle référence pour une tournée")
    private final String newReferenceTournee;
    @Schema(description = "Référence de la livraison")
    private final String referenceLivraison;
    @Schema(description = "Ancienne distance de la tournée")
    private final Double oldTourneeDistance;
    @Schema(description = "Nouvelle distance de la tournée")
    private final Double newTourneeDistance;
    @Schema(description = "Nouvel ordre")
    private final int newOrdre;
}
