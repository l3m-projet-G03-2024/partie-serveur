package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

public class NotFoundRestException extends RuntimeException{

    public NotFoundRestException(String message) {
        super(message);
    }
}

