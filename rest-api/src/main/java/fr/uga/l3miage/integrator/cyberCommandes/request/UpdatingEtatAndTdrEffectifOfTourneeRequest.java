package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Requête de mise à jour des attributs etat et tdrEffectif d'une tournée")
public class UpdatingEtatAndTdrEffectifOfTourneeRequest {

    @Schema(description = "la nouvelle état de la tournée")
    private final EtatsDeTournee etat ;

    @Schema(description = "le temps effectif de retour de la tournée")
    private final Integer tdrEffectf ;
}
