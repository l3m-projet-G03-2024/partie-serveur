package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.errors.CreateJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
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
    List<JourneeDetailResponseDTO> getAllJournees();

    @Operation(description = "Création d'une journée")
    @ApiResponse(responseCode = "201", description = "La journée a bien été créée")
    @ApiResponse(responseCode = "409", description = "Conflit avec l'état actuel de la ressource", content = @Content(schema = @Schema(implementation = CreateJourneeErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    JourneeDetailResponseDTO createJournee(@RequestBody JourneeCreationRequest journeeRequest) ;

    @ApiResponse(responseCode = "200",description = "liste de journée supprime avec succes")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la journée n'a pas été supprimée")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{referenceJournee}")
    void deleteJourneeById(@PathVariable(name = "referenceJournee") String reference);

    @Operation(description = "Prend une  journee")
    @ApiResponse(responseCode= "200", description = "La journée a est présent dans la base de donnée")
    @ApiResponse(responseCode = "404", description = "Cette journée n'exist pas")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{reference}")
    JourneeDetailResponseDTO getJourneeById(@PathVariable String reference);

    @Operation(description = "Update d'une journée")
    @ApiResponse(responseCode = "200", description = "La journée a bien été créée")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("{referenceJournee}")
    JourneeDetailResponseDTO updateJournee(@PathVariable(name = "referenceJournee") String reference, @RequestBody JourneeUpdateRequestDTO journeeDetailRequestDTO) ;

}
