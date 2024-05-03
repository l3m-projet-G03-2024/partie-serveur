package fr.uga.l3miage.integrator.cyberVitrine.controllers;

import fr.uga.l3miage.integrator.cyberVitrine.endpoints.CommandeEndPoints;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingBodyRequest;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeUpdateBodyResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommandeController implements CommandeEndPoints {
    private final CommandeService commandeService;

    public List<CommandeResponseDTO> getCommandes(EtatsDeCommande etat) {
       return commandeService.getCommandes(etat);
    }

    public List<CommandeResponseDTO> updateCommandes(CommandeUpdatingBodyRequest commandes) {
        return commandeService.updateCommandes(commandes);
    }
}
