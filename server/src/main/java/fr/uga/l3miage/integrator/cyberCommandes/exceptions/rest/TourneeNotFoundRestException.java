package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class TourneeNotFoundRestException extends RuntimeException {
    private final String reference;

    public TourneeNotFoundRestException(String message, String reference) {
        super(message);
        this.reference = reference;
    }

}
