package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class LivraisonTourneeRequest {
    @Schema(description =  "reference livraisons")
    private final String reference;
    @Schema(description =" ordre de la livraison")
    private final int ordre;
    @Schema(description = "eatat de la livraison")
    private final EtatsDeLivraison etatsDeLivraison;
    @Schema(description = "reference de la tournee")
    private final String referenceTournee;

}
