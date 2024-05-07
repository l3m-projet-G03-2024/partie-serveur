package fr.uga.l3miage.integrator.cyberProduit.exceptions.rest;

public class BadRequestRestException extends RuntimeException{
    public BadRequestRestException(String message) {
        super(message) ;
    }
}