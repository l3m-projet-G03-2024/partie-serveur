package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import java.util.List;
import java.util.Set;

import fr.uga.l3miage.integrator.cyberCommandes.errors.AddTourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.request.AddEmployeIdTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.CamionImmatriculationTouneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.UpdatingEtatAndTdrEffectifOfTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Gestion tournee", description="tous les endpoint de gestion d'une tournée")
@RequestMapping("api/v1/tournees")
@CrossOrigin("*")
public interface TourneeEndPoints {


    @Operation(description = "recupère une tournée")
    @ApiResponse(responseCode = "200",description ="la tournée a été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<TourneeResponseDTO> getAllTournees(@RequestParam(required = false) EtatsDeTournee etat, @RequestParam(required = false) String referenceJournee);

    @Operation(description = "crée des tournées pour une journée")
    @ApiResponse(responseCode = "201",description = "les tournées ont été créés")
    @ApiResponse(responseCode = "404",description ="les tournées n'ont pas été créés", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    TourneeCreationResponseDTO createTournees(@RequestBody TourneesCreationBodyRequest tourneeCreationBodyRequest);

    @Operation(description = "Ajouter un employé à une tournée")
    @ApiResponse(responseCode = "200",description = "L'employe a été ajouté à la tournée")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la tounee ou l'employe demandé n'a pas été trouvé",content = @Content(schema = @Schema(implementation = AddTourneeErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping("/{referenceTournee}/add-employe")
    TourneeResponseDTO addEmployeInTournee(@PathVariable(name = "referenceTournee")String referenceTournee, @RequestBody AddEmployeIdTourneeRequest addEmployeIdTourneeRequest);

    @Operation(description = "Récupération d'une tournée")
    @ApiResponse(responseCode = "200", description = "La tournée a été récupérée avec succès")
    @ApiResponse(responseCode = "404", description = "La tournée demandée n'a pas été trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{referenceTournee}")
    TourneeResponseDTO getTournee(@PathVariable(name = "referenceTournee") String referenceTournee) ;

    @Operation(description = "Recupérer toutes les tournées d'un employé (livreur)")
    @ApiResponse(responseCode = "200", description = "L'employé a été trouvé")
    @ApiResponse(responseCode = "404", description = "Aucun employé n'a cette adresse mail", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employes/{emailEmploye}")
    Set<TourneeResponseDTO> getAllTourneesByEmployeEmail(@PathVariable(name = "emailEmploye")String emailEmploye);

    @Operation(description = "Ajouter un camion dans tournee")
    @ApiResponse(responseCode = "200", description = "Camion a été ajouté dans tournee")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{referenceTournee}/add-camion")
    AddCamionOnTourneeResponseDTO addCamionOnTournee(@PathVariable(name = "referenceTournee") String referenceTournee, @RequestBody CamionImmatriculationTouneeRequest camionImmatriculationTouneeRequest);

    @Operation(description = "Mise à jour de l'état et du temps de retour effectif d'une tournée")
    @ApiResponse(responseCode = "200", description = "l'état et le temps de retour effectif de la tournée ont été modifiés avec succès")
    @ApiResponse(responseCode = "404", description = "La tournée demandée n'a pas été trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{referenceTournee}")
    TourneeResponseDTO updateEtatAndTdrEffectifOfTournee(@PathVariable(name = "referenceTournee") String referenceTournee, @RequestBody UpdatingEtatAndTdrEffectifOfTourneeRequest updatingEtatAndTdrEffectifOfTourneeRequest) ;

}
