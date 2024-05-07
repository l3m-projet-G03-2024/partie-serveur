package fr.uga.l3miage.integrator.cyberCommandes.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@Getter
public class LivraisonsCreationTourneeRequest {
    @Schema(description = "liste de livraisons ")
    private List<LivraisonTourneeRequest> livraisons;
    public LivraisonsCreationTourneeRequest() {
    }
    public LivraisonsCreationTourneeRequest(List<LivraisonTourneeRequest> livraisons) {
        this.livraisons = livraisons;
    }


}
