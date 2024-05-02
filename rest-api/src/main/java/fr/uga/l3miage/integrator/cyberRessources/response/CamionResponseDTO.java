package fr.uga.l3miage.integrator.cyberRessources.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Schema(description = "liste des camions dans un entrepot")
public class CamionResponseDTO {
    @Schema(description = "immatriculation du camion")
    private String immatriculation;
    @Schema(description = "latitude du camion")
    private Double latitude;
    @Schema(description = "longitude du camion")
    private Double longitude;

}
