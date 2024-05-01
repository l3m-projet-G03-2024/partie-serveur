package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.errors.CreateJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.DeleteJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.UpdateJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Journee endpoints")
@RequestMapping("/api/v1/journees")
@CrossOrigin("*")
public interface JourneeEndPoints {

    @ApiResponse(responseCode = "200",description = "liste de journée envoie avec success")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    Set<JourneeDetailResponseDTO> findAllJournees();

    @Operation(description = "Création d'une journée")
    @ApiResponse(responseCode = "201", description = "La journée a bien été créée")
    @ApiResponse(responseCode = "409", description = "Conflit avec l'état actuel de la ressource", content = @Content(schema = @Schema(implementation = CreateJourneeErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    JourneeDetailResponseDTO createJournee(@RequestBody JourneeCreationRequest journeeRequest) ;

    @ApiResponse(responseCode = "200",description = "liste de journée supprime avec success")
    @ApiResponse(responseCode = "404", description = "La journée n'a pas été trouvée", content = @Content(schema = @Schema(implementation = DeleteJourneeErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400", description = "Requisition invalide", content = @Content(schema = @Schema(implementation = DeleteJourneeErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{referenceJourner}")
    void deleteJourneeById(@PathVariable(name = "referenceJourner") String referenceJourner);

    @Operation(description = "Prend une  journee")
    @ApiResponse(responseCode= "200", description = "La journée a est présent dans la base de donnée")
    @ApiResponse(responseCode = "404", description = "Cette journée n'exist pas")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{reference}")
    JourneeDetailResponseDTO getJourneeById(@PathVariable String reference);

    @Operation(description = "Update d'une journée")
    @ApiResponse(responseCode = "200", description = "La journée a bien été créée")
    @ApiResponse(responseCode = "404", description = "La journée n'a pas été trouvée", content = @Content(schema = @Schema(implementation = UpdateJourneeErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "400", description = "Requisition invalide", content = @Content(schema = @Schema(implementation = UpdateJourneeErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{referenceJournee}")
    JourneeDetailResponseDTO updateJournee(@PathVariable(name = "referenceJournee") String reference, @RequestBody JourneeUpdateRequest journeeRequestDTO) ;

}
