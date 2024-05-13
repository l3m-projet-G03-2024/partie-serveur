package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LivraisonUpdateRequest {
    @Schema(description = "etat de la livraison")
    private final EtatsDeLivraison etat;
    @Schema(description = "date de livraison")
    private final LocalDateTime date;

}
