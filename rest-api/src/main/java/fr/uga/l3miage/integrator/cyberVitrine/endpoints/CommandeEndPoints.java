package fr.uga.l3miage.integrator.cyberVitrine.endpoints;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Commande endoints")
@RequestMapping("api/v1/commandes")
public interface CommandeEndPoints {


    @ApiResponse(responseCode = "200",description = "liste de commande envoyée avec succès")
    @ApiResponse(responseCode = "404", description = "Aucune commande trouvée pour l'état spécifié")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<CommandeResponseDTO> getCommandes(@RequestParam(required = false) EtatsDeCommande etat);

    @ApiResponse(responseCode = "200", description = "la liste des commandes a été modifiée avec succès")
    @ApiResponse(responseCode = "400", description = "données fournies incorrectes ou manquantes")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("")
    List<CommandeResponseDTO> updateCommandes(@RequestBody List<CommandeUpdatingRequest> commandes) ;


}
