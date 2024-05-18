package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonUpdateRequest {
    @Schema(description = "etat de la livraison")
    private EtatsDeLivraison etat;
    private Integer tdpEffectif;
    private Integer tdcEffectif;
    private Integer tecEffectif;
    private Integer tdmEffectif;
}
