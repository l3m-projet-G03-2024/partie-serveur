package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.errors.BadRequestErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.ForbiddenErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.NotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.errors.UpdateFailedErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonUpdateResponseDTO;
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
@Tag(name = "Gestion des Livraisons")
@RequestMapping("/api/v1/livraisons")
@CrossOrigin("*")
public interface LivraisonEndPoints {


    @Operation(description = "Récupère les livraisons en fonction d'un état")
    @ApiResponse(responseCode = "200", description = "liste de livraison envoyé avec succès")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<LivraisonResponseDTO> getLivraisons(@RequestParam(required = false) EtatsDeLivraison etat);

    @Operation(description = "Création des livraisons")
    @ApiResponse(responseCode = "201", description = "Livraisons crées avec succès")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit l'ors de la création ", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    LivraisonCreationResponseDTO createLivraisons(@RequestBody() LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest);

    @Operation(description = "Mise à jour d'une livraison")
    @ApiResponse(responseCode = "200", description = "Mise à jour réussie")
    @ApiResponse(responseCode = "400", description = "Requête invalide", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "403", description = "Accès refusé", content = @Content(schema = @Schema(implementation = ForbiddenErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "404", description = "Livraison non trouvée", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "500", description = "Échec de la mise à jour", content = @Content(schema = @Schema(implementation = UpdateFailedErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{referenceLivraison}")
    LivraisonUpdateResponseDTO updateLivraison(@PathVariable(name = "referenceLivraison") String reference, @RequestBody LivraisonUpdateRequest livraisonUpdateRequest);

    @Operation(description = "Récupère une livraison et ses commandes à partir d'une référence livraison")
    @ApiResponse(responseCode = "200", description = "récupère pour chaque livraison, sa commande et le client")
    @ApiResponse(responseCode = "404", description = "Cette livraison n'existe pas", content = @Content(schema = @Schema(implementation = NotFoundErrorResponse.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{referenceLivraison}")
    LivraisonResponseDTO getLivraisonDetailCommande(@PathVariable(name = "referenceLivraison") String referenceLivraison);

}
