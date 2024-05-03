package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeLivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Representation d'une livraison")
public class LivraisonResponseDTO {
    @Schema(description = "Reference de la livraison", example = "T2")
    private String reference;


    @Schema(description = "L'etat de la livraison", example = "ENPARCOURS")
    private EtatsDeLivraison etat;

    @Schema(description = "l'ordre de livraison", example = "1")
    private Integer ordre;

    private Set<CommandeResponseDTO> commandes;

}
