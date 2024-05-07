package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class NotFoundRestException extends RuntimeException{
    private String reference;
    public NotFoundRestException(String message) {
        super(message);
    }
    public NotFoundRestException(String message, String reference) {
        super(message);
        this.reference = reference;
    }
}

