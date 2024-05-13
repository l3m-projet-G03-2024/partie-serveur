package fr.uga.l3miage.integrator.cyberRessources.endpoints;

import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Camion enpoint")
@RequestMapping("/api/v1/camions")
@CrossOrigin("*")
public interface CamionEnpoints {

    @Operation(description = "recupère liste des camions")
    @ApiResponse(responseCode = "200",description ="liste des camions a été trouvée")
    @ApiResponse(responseCode = "404", description = "l'entrepot ou le nom demandée n'a pas été trouvé")

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<CamionResponseDTO> getCamionsByIdEntrepot(@RequestParam(required = false) String nomEntrepot);

}
