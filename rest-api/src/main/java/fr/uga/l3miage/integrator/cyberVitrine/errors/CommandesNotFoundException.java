package fr.uga.l3miage.integrator.cyberVitrine.errors;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Builder
public class CommandesNotFoundException extends Exception{

    @Schema(description = "end point call", example = "/api/drone/")
    private final String uri;
    @Schema(description = "error message", example = "journée inexistante")
    private final String errorMessage;


}
