package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class JourneeNotFoundRestException extends RuntimeException {
    private final String reference;

    public JourneeNotFoundRestException(String message, String reference) {
        super(message);
        this.reference = reference;
    }

}
