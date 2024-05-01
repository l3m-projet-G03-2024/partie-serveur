package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class JourneeResponseDTO {
    @Schema(description = "represente la reference de la journée")
    private String reference;
    @Schema(description = "date de la journée")
    private LocalDate date;
    @Schema(description = "état de la journée")
    private EtatsDeJournee etat;
}
