package fr.uga.l3miage.integrator.cyberVitrine.errors.rest;

public class BadRequestRestException extends RuntimeException{
    public BadRequestRestException(String message) {
        super(message) ;
    }
}
