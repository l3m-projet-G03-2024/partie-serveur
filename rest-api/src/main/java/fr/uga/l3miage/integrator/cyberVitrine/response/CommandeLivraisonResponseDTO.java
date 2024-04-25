package fr.uga.l3miage.integrator.cyberVitrine.response;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "represente les details de commande de chaque livraison")
public class CommandeLivraisonResponseDTO {

    private String reference;
    private EtatsDeCommande etatsDeCommande;

}
