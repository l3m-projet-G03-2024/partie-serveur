package fr.uga.l3miage.integrator.cyberVitrine.endpoints;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
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


    @ApiResponse(responseCode = "200",description = "liste de commande envoyer avec succès")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la liste de commande n'a pas été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<CommandeResponseDTO> getCommandes(@RequestParam(required = false) EtatsDeCommande etat);


}
