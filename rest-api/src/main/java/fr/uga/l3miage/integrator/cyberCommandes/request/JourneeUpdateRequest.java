package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeUpdateRequest {

    @Schema(description = "Etat possible d'une journee")
    private final EtatsDeJournee etat;
    @Schema(description = "date de la journee")
    private final LocalDate date;
    @Schema(description = "distance a parcourir")
    private final Double distanceAParcourir;
    @Schema(description = "montant total")
    private final Double montant;
    @Schema(description = "temps de montage theorique")
    private final Integer tdmTheorique;
}

