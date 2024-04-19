package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Journee endpoints")
@RequestMapping("/api/v1/journee")
public interface JourneeEndPoints {

    @ApiResponse(responseCode = "200",description = "liste de journée envoie avec succes")
    @ApiResponse(responseCode = "404", description = "Une erreur c'est produit, la liste de journee n'a pas été trouvé")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/journees")
    List<JourneeResponseDTO> getAllJournees();
}
