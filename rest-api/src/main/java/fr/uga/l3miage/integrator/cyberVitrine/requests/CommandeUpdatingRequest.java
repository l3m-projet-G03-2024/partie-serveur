package fr.uga.l3miage.integrator.cyberVitrine.requests;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeUpdatingRequest {

    private String reference ;

    private EtatsDeCommande etat ;

    private String referenceLivraison ;


}
