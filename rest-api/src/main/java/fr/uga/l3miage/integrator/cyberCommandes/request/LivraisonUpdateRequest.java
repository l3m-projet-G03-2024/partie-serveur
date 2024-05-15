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
    @Schema(description = "Temps de parcours effectif")
    private final Integer tdpEffectif;
    @Schema(description = "Temps de chargement effectif")
    private final Integer tdcEffectif;
    @Schema(description = "Temps en clientele effectif")
    private final Integer tecEffectif;
    @Schema(description = "Temps de montage effectif")
    private final Integer tdmEffectif;
    @Schema(description = "Temps de chargement effectif")
    private final Integer tddEffectif;

}
