package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

public class DeleteFailedRestException extends RuntimeException{
    public DeleteFailedRestException(String message){
        super(message);
    }
}
