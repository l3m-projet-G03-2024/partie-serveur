package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TourneeUpdateLivraisonRequest {
    private final String newReferenceTournee;
    private final String referenceLivraison;
    private final Double oldTourneeDistance;
    private final Double newTourneeDistance;
    private final int newOrdre;
}
