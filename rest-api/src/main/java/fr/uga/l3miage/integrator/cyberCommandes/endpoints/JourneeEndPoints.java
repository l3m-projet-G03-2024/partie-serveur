package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.errors.CreateJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Journee endpoints")
@RequestMapping("/api/v1/journees")
@CrossOrigin("*")
public interface JourneeEndPoints {

    @ApiResponse(responseCode = "200",description = "liste de journée envoie avec succes")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la liste de journée n'a pas été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<JourneeResponseDTO> getAllJournees();


    @Operation(description = "Création d'une journée")
    @ApiResponse(responseCode = "201", description = "La journée a bien été créée")
    @ApiResponse(responseCode = "409", description = "Conflit avec l'état actuel de la ressource", content = @Content(schema = @Schema(implementation = CreateJourneeErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    JourneeResponseDTO createJournee(@RequestBody JourneeRequest journeeRequest) ;

    @ApiResponse(responseCode = "200",description = "liste de journée supprime avec succes")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la journée n'a pas été supprimée")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{referenceJourner}")
    void deleteJourneeById(String reference);

}
