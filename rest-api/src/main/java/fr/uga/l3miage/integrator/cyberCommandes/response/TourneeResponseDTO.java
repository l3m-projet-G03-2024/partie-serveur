package fr.uga.l3miage.integrator.cyberCommandes.response;

import java.util.Set;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "présentation d'une tournée")
public class TourneeResponseDTO {

    @Schema(description = "reference d'une tournée")
    private String reference;

    @Schema(description = "l'atat d'une tournée")
    private EtatsDeTournee etatsDeTournee;

    @Schema(description = "la distance de la tournée")
    private Double distance;

    @Schema(description="liste des livraisons")
    private Set<LivraisonResponseDTO> livraisonEntities;

    @Schema(description="reference d'une journée ")
    private JourneeDetailResponseDTO journee;

}
