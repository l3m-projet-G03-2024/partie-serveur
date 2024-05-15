package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.errors.*;
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

import java.util.List;

@RestController
@Tag(name = "Journee endpoints")
@RequestMapping("/api/v1/journees")
@CrossOrigin("*")
public interface JourneeEndPoints {

    @Operation(description = "Lister toutes les journée")
    @ApiResponse(responseCode = "200",description = "liste de journée envoie avec success")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<JourneeDetailResponseDTO> findAllJournees();

    @Operation(description = "Création d'une journée")
    @ApiResponse(responseCode = "200", description = "La journée a bien été créée")
    @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé", content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Conflit avec l'état actuel de la ressource", content = @Content(schema = @Schema(implementation = CreationFailedErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    JourneeDetailResponseDTO createJournee(@RequestBody JourneeCreationRequest journeeRequest) ;
    @Operation(description = "Supprimer une journée par référence")
    @ApiResponse(responseCode = "200",description = "La journée a été supprimée avec succès")
    @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé", content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Journée non trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Échec de la suppression de la journée", content = @Content(schema = @Schema(implementation = DeleteFailedErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{referenceJournee}")
    void deleteJourneeById(@PathVariable(name = "referenceJournee") String referenceJournee);

    @Operation(description = "Récupère une journée à partir d'une référence")
    @ApiResponse(responseCode = "200", description = "La journée a est présent dans la base de donnée")
    @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé", content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Journée non trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Échec de la recherche d'une journée", content = @Content(schema = @Schema(implementation = DeleteFailedErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{reference}")
    JourneeDetailResponseDTO getJourneeById(@PathVariable String reference);

    @Operation(description = "Mise à jour d'une journée")
    @ApiResponse(responseCode = "200", description = "")
    @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé", content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Journée non trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Échec de la mise à jour", content = @Content(schema = @Schema(implementation = UpdateFailedErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{referenceJournee}")
    JourneeDetailResponseDTO updateJournee(@PathVariable(name = "referenceJournee") String reference, @RequestBody JourneeUpdateRequest journeeRequestDTO) ;

}
