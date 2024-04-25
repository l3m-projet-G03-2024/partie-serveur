package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class JourneeNotFoundException extends RuntimeException {
    private final String reference;

    public JourneeNotFoundException(String message, String reference) {
        super(message);
        this.reference = reference 
        ;
    }

}
