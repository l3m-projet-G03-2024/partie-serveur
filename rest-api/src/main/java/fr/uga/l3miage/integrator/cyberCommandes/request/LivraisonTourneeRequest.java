package fr.uga.l3miage.integrator.cyberCommandes.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LivraisonTourneeRequest {
    @Schema(description =  "reference livraisons")
    private final String reference;
    private final int ordre;
    private final EtatsDeLivraison etatsDeLivraison;
    private final String referenceTournee;



}
