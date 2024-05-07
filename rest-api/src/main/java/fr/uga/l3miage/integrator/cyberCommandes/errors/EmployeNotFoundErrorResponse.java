package fr.uga.l3miage.integrator.cyberCommandes.errors;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeNotFoundErrorResponse {
    @Schema(description = "L'adresse mail d'un employe", example = "test.test1@gmail.com")
    private final String emailEmploye;
    @Schema(description = "end point call", example = "/api/drone/")
    private final String uri;
    @Schema(description = "error message", example = "L'email Ladycire@gmail.com n'appartient à aucun employé")
    private final String errorMessage;
}
