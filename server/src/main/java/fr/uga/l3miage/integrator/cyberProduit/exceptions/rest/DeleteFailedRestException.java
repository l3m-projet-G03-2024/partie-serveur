package fr.uga.l3miage.integrator.cyberProduit.exceptions.rest;

public class DeleteFailedRestException extends RuntimeException{
    public DeleteFailedRestException(String message){
        super(message);
    }
}
