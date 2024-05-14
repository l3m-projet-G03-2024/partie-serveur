package fr.uga.l3miage.integrator.cyberVitrine.requests;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandeUpdatingRequest {

    private String reference ;

    private EtatsDeCommande etat ;

    private String referenceLivraison ;


}
