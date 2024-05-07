package fr.uga.l3miage.integrator.cyberVitrine.endpoints;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.BadRequestErrorResponse;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingBodyRequest;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeUpdateBodyResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.DetailsCommandeResponseDTO;
import io.swagger.annotations.Api;
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
@Tag(name = "Commande endoints")
@RequestMapping("api/v1/commandes")
@CrossOrigin("*")
public interface CommandeEndPoints {

    @Operation(description = "")
    @ApiResponse(responseCode = "200",description = "liste de commande envoyée avec succès")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<CommandeResponseDTO> getCommandes(@RequestParam(required = false) EtatsDeCommande etat);

    @Operation(description = "Modification des commandes")
    @ApiResponse(responseCode = "200", description = "la liste des commandes a été modifiée avec succès")
    @ApiResponse(responseCode = "400", description = "Requête incorrecte ou mal formée", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/")
    List<CommandeResponseDTO> updateCommandes(@RequestBody CommandeUpdatingBodyRequest commandeUpdatingBodyRequest) ;



    @Operation(description = "Récupération des details d'une Commande")
    @ApiResponse(responseCode = "200",description = "détails de commande envoyées avec succès")
    @ApiResponse(responseCode = "400", description = "Requête incorrecte ou mal formée", content = @Content(schema = @Schema(implementation = BadRequestErrorResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{referenceCommande}")
    DetailsCommandeResponseDTO getDetailsCommande(@PathVariable(name = "referenceCommande")String referenceCommande);


}
