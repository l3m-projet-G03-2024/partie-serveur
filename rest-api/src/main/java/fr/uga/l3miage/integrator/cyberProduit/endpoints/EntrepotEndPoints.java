package fr.uga.l3miage.integrator.cyberProduit.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@Tag(name = "Entrepot endpoints")
@RequestMapping("/api/v1/entrepots")
@CrossOrigin("*")
public interface EntrepotEndPoints {
    @ApiResponse(responseCode = "200",description = "la liste des entrepots a été envoyé avec succèss")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<EntrepotResponseDetailDTO> getAllEntrepots();

}
