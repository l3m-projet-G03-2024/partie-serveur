package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Gestion des Livraisons")
@RequestMapping("/api/v1/livraisons")
@CrossOrigin("*")
public interface LivraisonEndPoints {


    @ApiResponse(responseCode = "200",description = "liste de livraison envoyer avec succès")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<LivraisonResponseDTO> getLivraisons(@RequestParam(required = false) EtatsDeLivraison etat);

    @ApiResponse(responseCode = "200",description = "Livraisons crées avec succès")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit l'ors de la création ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    LivraisonCreationResponseDTO createLivraisons(@RequestBody() LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest);


}
