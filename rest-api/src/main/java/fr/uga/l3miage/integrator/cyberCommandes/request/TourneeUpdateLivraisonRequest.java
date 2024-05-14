package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TourneeUpdateLivraisonRequest {
    private final Double distance;
    private Set<LivraisonResponseDTO> livraisons;
}
