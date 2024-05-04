package fr.uga.l3miage.integrator.cyberCommandes.response;

import java.util.Set;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;

import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "présentation d'une tournée")
public class TourneeResponseDTO {

    @Schema(description = "reference d'une tournée",example = "T1J1")
    private String reference;

    @Schema(description = "l'atat d'une tournée", example = "PLANIFIEE")
    private EtatsDeTournee etat;

    @Schema(description = "la distance de la tournée",example = "")
    private Double distance;

    @Schema(description="liste des livraisons")
    private Set<LivraisonResponseDTO> livraisons;

    @Schema(description="details de la journée de la tournée")
    private JourneeDetailResponseDTO journee;

     @Schema(description = "details des employes")
     private Set<EmployeResponseDTO> employes;

     @Schema(description = "details du camion")
     private CamionResponseDTO camion;

}
