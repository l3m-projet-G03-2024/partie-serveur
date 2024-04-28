package fr.uga.l3miage.integrator.cyberCommandes.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "creation des tournées d'une journée")
public class TourneeCreationResponseDTO {

    @Schema(description = "echec ou reussite de la création des tournées")
    private boolean success;
    @Schema(description = "message d'echec ou de succes de création des tournées d'une journée")
    private String message;


    public TourneeCreationResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
