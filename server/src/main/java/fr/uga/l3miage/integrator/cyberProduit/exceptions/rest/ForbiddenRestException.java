package fr.uga.l3miage.integrator.cyberProduit.exceptions.rest;

public class ForbiddenRestException extends RuntimeException{
    public ForbiddenRestException(String message){
        super(message);
    }
}
