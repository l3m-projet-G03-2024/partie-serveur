package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Requête de mise à jour des attributs etat et tdrEffectif d'une tournée")
@AllArgsConstructor
@NoArgsConstructor
public class UpdatingEtatAndTdrEffectifOfTourneeRequest {

    @Schema(description = "la référence de la tournée")
    private  String referenceTournee ;

    @Schema(description = "la nouvelle état de la tournée")
    private  EtatsDeTournee etat ;

    @Schema(description = "le temps effectif de retour de la tournée")
    private  Integer tdrEffectf ;
}
