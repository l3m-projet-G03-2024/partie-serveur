package fr.uga.l3miage.integrator.cyberCommandes.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema( description = "Création des livraisons d'une tournée")
public class LivraisonCreationResponseDTO {
    @Schema(description = " echec ou reussite de la création")
    private boolean succes;
    @Schema(description =  "message du reponse")
    private String message;

    //private int createdLivraisons;

    public LivraisonCreationResponseDTO( boolean succes, String message){
        this.succes = succes;
        this.message = message;

    }
}