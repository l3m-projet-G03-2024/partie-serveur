package fr.uga.l3miage.integrator.cyberVitrine.response;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DetailsCommandeResponseDTO {

    private String reference;

    private EtatsDeCommande etat;

    private Set<LigneCommandeResponseDTO> lignes;


}
