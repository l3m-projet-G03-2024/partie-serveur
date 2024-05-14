package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
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
public class JourneeCreationRequest {
    @Schema(description = "represente la reference de la journée")
    private String reference ;
    @Schema(description = "date de la journée")
    private LocalDate date ;
    @Schema(description = "entrepôts associée à une journée")
    private String nomEntrepot;
}
