package fr.uga.l3miage.integrator.cyberProduit.exceptions.technical;

import lombok.Getter;

@Getter
public class EntrepotNotFoundException extends Exception{
    private final String reference;
    public EntrepotNotFoundException(String message, String reference){
        super(message);
        this.reference = reference;
    }

}
