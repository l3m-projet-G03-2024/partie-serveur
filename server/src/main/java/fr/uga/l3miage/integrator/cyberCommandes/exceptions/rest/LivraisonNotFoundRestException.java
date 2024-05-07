package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class LivraisonNotFoundRestException extends RuntimeException {
    private  final String reference;
    public LivraisonNotFoundRestException(String message, String reference) {
        super(message);
        this.reference = reference;
    }
}
