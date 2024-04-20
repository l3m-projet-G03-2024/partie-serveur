package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

public class NotFoundEntityRestException extends RuntimeException{

    public NotFoundEntityRestException(String message) {
        super(message);
    }
}

