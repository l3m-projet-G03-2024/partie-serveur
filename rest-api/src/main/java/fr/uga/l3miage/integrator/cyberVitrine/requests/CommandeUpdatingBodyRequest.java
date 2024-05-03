package fr.uga.l3miage.integrator.cyberVitrine.requests;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommandeUpdatingBodyRequest {

    private List<CommandeUpdatingRequest> commandes;

    public CommandeUpdatingBodyRequest() {

    }

    public CommandeUpdatingBodyRequest(List<CommandeUpdatingRequest> commandes) {
        this.commandes = commandes;
    }

}
