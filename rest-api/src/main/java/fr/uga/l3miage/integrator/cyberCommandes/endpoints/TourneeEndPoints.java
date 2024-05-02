package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Gestion tournee", description="tous les endpoint de gestion d'une tournée")
@RequestMapping("api/v1/tournees")
@CrossOrigin(value = "*")
public interface TourneeEndPoints {
    
    @Operation(description = "recupère une tournée")
    @ApiResponse(responseCode = "200",description ="la tournée a été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<TourneeResponseDTO> getAllTournees(@RequestParam(required = false) EtatsDeTournee etat, @RequestParam(required = false) String referenceJournee);

    @Operation(description = "crée des tournées pour une journée")
    @ApiResponse(responseCode = "201",description = "les tournées ont été créés")
    @ApiResponse(responseCode = "404",description ="les tournées n'ont pas été créés")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    TourneeCreationResponseDTO createTournees(@RequestBody TourneesCreationBodyRequest tourneeCreationBodyRequest);



}
