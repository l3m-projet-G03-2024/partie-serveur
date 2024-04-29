package fr.uga.l3miage.integrator.cyberVitrine.response;

import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor

@Schema(description = "represente les commandes d'un client")
public class CommandeResponseDTO {

    @Schema(description = "la reference de la commande")
    private String reference;

    @Schema(description = "l'état de la commande")
    private EtatsDeCommande etat;

    @NotNull
    @Schema(description = "date de la création de la commande")
    private LocalDate dateDeCreation;

    @Schema(description = "details necessaires du client")
    private ClientDetailResponseDTO client;

    @Schema(description = "référence de la livraison de la commande")
    private LivraisonResponseDTO livraison ;
}
