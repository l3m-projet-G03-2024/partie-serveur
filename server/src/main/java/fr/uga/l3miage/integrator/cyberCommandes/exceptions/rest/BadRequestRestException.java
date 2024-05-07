package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

public class BadRequestRestException extends RuntimeException{
    public BadRequestRestException(String message) {
        super(message) ;
    }
}