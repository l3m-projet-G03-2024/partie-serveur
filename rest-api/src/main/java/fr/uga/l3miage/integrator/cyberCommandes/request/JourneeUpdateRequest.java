package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourneeUpdateRequest {

    @Schema(description = "Etat possible d'une journee")
    private EtatsDeJournee etat;
    @Schema(description = "date de la journee")
    private  LocalDate date;
}

