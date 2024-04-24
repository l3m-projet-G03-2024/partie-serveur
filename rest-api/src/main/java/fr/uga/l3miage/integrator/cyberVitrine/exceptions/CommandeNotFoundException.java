package fr.uga.l3miage.integrator.cyberVitrine.exceptions;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import lombok.Getter;

@Getter
public class CommandeNotFoundException extends Exception{
    private final EtatsDeCommande etatsDeCommande;

    public CommandeNotFoundException(String message, EtatsDeCommande etatsDeCommande) {
        super(message);
        this.etatsDeCommande = etatsDeCommande;
    }


}
