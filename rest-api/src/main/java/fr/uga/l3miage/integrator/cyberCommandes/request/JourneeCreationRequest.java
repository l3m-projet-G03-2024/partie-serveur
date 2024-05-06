package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeCreationRequest {
    @Schema(description = "represente la reference de la journée")
    private final String reference ;
    @Schema(description = "date de la journée")
    private final LocalDate date ;
    @Schema(description = "")
    private final String nomEntrepot;
}
