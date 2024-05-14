package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class LivraisonUpdateRequest {
    @Schema(description = "etat de la livraison")
    private final EtatsDeLivraison etat;
    private final Integer tdpEffectif;
    private final Integer tdcEffectif;
    private final Integer tecEffectif;
    private final Integer tdmEffectif;
    private final Integer tddEffectif;

}
