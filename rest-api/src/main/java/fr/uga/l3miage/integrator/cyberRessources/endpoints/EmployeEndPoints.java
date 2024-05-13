package fr.uga.l3miage.integrator.cyberRessources.endpoints;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.BadRequestErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Employe endpoints")
@RequestMapping("/api/v1/employes")
@CrossOrigin("*")
public interface EmployeEndPoints {

    @ApiResponse(responseCode = "200",description = "La liste des employes")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    Set<EmployeResponseDTO> listEmployeByEmploiOrNomEntrepot(@RequestParam(required = false) Emploi emploi, @RequestParam(required = false) String nomEntrepot);


    @Operation(description = "Récupération des détails d'un employe")
    @ApiResponse(responseCode = "200", description = "détails de l'employé envoyées avec succès")
    @ApiResponse(responseCode = "400", description = "Requête incorrect ou mal formée", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{emailEmploye}")
    EmployeResponseDTO getDetailsEmploye(@PathVariable(name = "emailEmploye")String emailEmploye);

}
