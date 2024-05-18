package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourneeUpdateLivraisonRequest {
    private String newReferenceTournee;
    private String referenceLivraison;
    private Double oldTourneeDistance;
    private Double newTourneeDistance;
    private int newOrdre;
}
