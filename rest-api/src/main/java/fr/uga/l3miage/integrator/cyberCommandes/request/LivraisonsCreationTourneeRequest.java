package fr.uga.l3miage.integrator.cyberCommandes.request;

import java.util.List;

import lombok.*;

@Data
@Builder
@Getter
public class LivraisonsCreationTourneeRequest {
    private   List<LivraisonTourneeRequest> livraisons;
    public LivraisonsCreationTourneeRequest() {
    }
    public LivraisonsCreationTourneeRequest(List<LivraisonTourneeRequest> livraisons) {
        this.livraisons = livraisons;
    }


}
