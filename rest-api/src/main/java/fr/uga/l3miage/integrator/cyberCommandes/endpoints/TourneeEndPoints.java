package fr.uga.l3miage.integrator.cyberCommandes.endpoints;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Gestion tournee", description="tous les endpoint de gestion d'une tournée")
@RequestMapping("api/v1/tournees")
@CrossOrigin("*")
public interface TourneeEndPoints {
    
    @Operation(description = "reupère une tournée")
    @ApiResponse(responseCode = "200",description ="la tournée a été trouvée")
    @ApiResponse(responseCode = "404",description ="la tournée n'a pas été trouvée")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    List<TourneeResponseDTO> getAllTournee(@RequestParam(required = false) EtatsDeTournee etat, @RequestParam(required = false) String referenceJournee);



}
