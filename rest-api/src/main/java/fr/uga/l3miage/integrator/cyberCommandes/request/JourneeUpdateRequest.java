package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeUpdateRequest {

    @Schema(description = "")
    private final EtatsDeJournee etat;
    @Schema(description = "")
    private final LocalDate date;
    @Schema(description = "")
    private final Double distanceAParcourir;
    @Schema(description = "")
    private final Double montant;
    @Schema(description = "")
    private final Integer tdmTheorique;
    @Schema(description = "")
    private final String nomEntrepot;
}

