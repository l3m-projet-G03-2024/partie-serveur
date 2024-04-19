package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class LivraisonCreationRequest {
    private final String referenceCommande;

    private final String dateLivraison;
}
