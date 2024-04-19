package fr.uga.l3miage.integrator.cyberCommandes.response;

import java.util.Date;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "présentation d'une journée")
public class JourneeDetailResponseDTO {
    @Schema(description = "represente la reference de la journee")
    private String reference;

    @Schema(description = "etat de la journée")
    private EtatsDeJournee etat;

    @Schema(description = "date de la journéee")
    private Date date;
//pour ce premier increment pas necessairement besoin de calculer ces attributs
// Pour le sécond incrément on ajoutera les information manquantes
    @Schema(description = "somme des distances a parcourir dans la journée")
    private Double distanceAParcourir;
    @Schema(description = "montan total de la journée")
    private Double montant;

    // @Schema(description = "temps de retour estimer")
    // private Integer tdrTheorique;
    

}

