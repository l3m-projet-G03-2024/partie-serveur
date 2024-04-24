package fr.uga.l3miage.integrator.cyberVitrine.response;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "les details d'un client")
public class ClientDetailResponseDTO {

    @Schema(description = "email du client")
    private String email;
    @Schema(description = "latitude de la position du client")
    private Double latitude;
    @Schema(description = "longitude de la position du client")
    private Double longitude;

}
