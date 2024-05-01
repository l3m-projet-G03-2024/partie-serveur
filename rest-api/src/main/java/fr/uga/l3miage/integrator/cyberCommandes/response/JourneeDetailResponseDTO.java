package fr.uga.l3miage.integrator.cyberCommandes.response;

import java.time.LocalDate;
import java.util.Date;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "présentation d'une journée")
public class JourneeDetailResponseDTO {
    @Schema(description = "represente la reference de la journée")
    private String reference;

    @Schema(description = "état de la journée")
    private EtatsDeJournee etat;

    @Schema(description = "date de la journée")
    private LocalDate date;

    @Schema(description = "somme des distances a parcourir dans la journée")
    private Double distanceAParcourir;
    
    @Schema(description = "montant total de la journée")
    private Double montant;

    @Schema(description = "temps de retour estimer")
    private Integer tdrTheorique;
    

}

