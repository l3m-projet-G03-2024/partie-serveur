package fr.uga.l3miage.integrator.cyberRessources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Schema(description = "liste des camions dans un entrepot")
@AllArgsConstructor
@NoArgsConstructor
public class CamionResponseDTO {
    @Schema(description = "immatriculation du camion")
    private String immatriculation;
    @Schema(description = "latitude du camion")
    private Double latitude;
    @Schema(description = "longitude du camion")
    private Double longitude;

}
