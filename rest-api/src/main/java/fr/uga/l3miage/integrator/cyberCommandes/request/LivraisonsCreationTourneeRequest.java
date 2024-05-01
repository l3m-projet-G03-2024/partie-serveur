package fr.uga.l3miage.integrator.cyberCommandes.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class LivraisonsCreationTourneeRequest {
    private   List<LivraisonTourneeRequest> livraisons;
    public LivraisonsCreationTourneeRequest() {
    }
    public LivraisonsCreationTourneeRequest(List<LivraisonTourneeRequest> livraisons) {
        this.livraisons = livraisons;
    }



}
