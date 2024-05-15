package fr.uga.l3miage.integrator.cyberCommandes.response;


import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCamionOnTourneeResponseDTO {
    @Schema(description = "trigramme d'employe")
    private String reference;
    @Schema(description = "trigramme d'employe")
    private CamionResponseDTO camion;
}

