package fr.uga.l3miage.integrator.cyberCommandes.errors;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotFoundErrorResponse {
    private final String uri;
    @Schema(description = "error message", example = "La tournee n°1 n'existe pas")
    private final String errorMessage;
}
