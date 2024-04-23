package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Representation d'une livraison")
public class LivraisonResponseDTO {
    @Schema(description = "Reference de la livraison")
    private String reference;

    @Schema(description = "Date de la livraison")
    private LocalDateTime date;

    @Schema(description = "L'etat de la livraison")
    private EtatsDeLivraison etat;

}
