package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Gestion des Livraisons")
@RequestMapping("/api/livraisons")
public interface LivraisonEndPoints {

    // @Operation(description = "Création d'une Livraison")
    // @ApiResponse(responseCode = "201",description = "La Livraison à bien été créé")
    // @ApiResponse(responseCode = "400",description = "une erreur c'est produit avec la requête")
    // @ResponseStatus(HttpStatus.CREATED)
    // @PostMapping("/create")
    // LivraisonResponseDTO createLivraison(@RequestBody LivraisonCreationRequest livraisonCreationRequest);

    @ApiResponse(responseCode = "200",description = "liste de livraison envoyer avec succès")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la liste de livraison n'a pas été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<LivraisonResponseDTO> getLivraisons(@RequestParam(required = false) EtatsDeLivraison etat);

    @ApiResponse(responseCode = "200",description = "Livraisons crées avec succès")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit l'ors de la création ")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    LivraisonCreationResponseDTO createLivraisons(@RequestBody() LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest);


}
