package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
