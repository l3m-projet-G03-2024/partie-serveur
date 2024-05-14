package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonTourneeRequest {
    @Schema(description =  "reference livraisons")
    private String reference;
    @Schema(description =" ordre de la livraison")
    private int ordre;
    @Schema(description = "etat de la livraison")
    private EtatsDeLivraison etat;
    @Schema(description = "reference de la tournee")
    private String referenceTournee;

}
