package fr.uga.l3miage.integrator.cyberCommandes.request;

import lombok.*;



@Data
@Builder
public class LivraisonCreationRequest {
    private final String referenceCommande;

    private final String dateLivraison;
}
