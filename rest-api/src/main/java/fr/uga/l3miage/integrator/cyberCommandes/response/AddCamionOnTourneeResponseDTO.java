package fr.uga.l3miage.integrator.cyberCommandes.response;


import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCamionOnTourneeResponseDTO {
    private String reference;
    private CamionResponseDTO camion;
}

