package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Representation d'une livraison")
public class LivraisonUpdateResponseDTO {
    private String reference;
    private EtatsDeLivraison etat;
    private Integer tdpEffectif;
    private Integer tdcEffectif;
    private Integer tecEffectif;
    private Integer tdmEffectif;
    private Integer tddEffectif;
    private Set<CommandeResponseDTO> commandes;
}
