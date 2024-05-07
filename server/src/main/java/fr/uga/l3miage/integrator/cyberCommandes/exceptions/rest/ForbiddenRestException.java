package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

public class ForbiddenRestException extends RuntimeException{
    public ForbiddenRestException(String message){
        super(message);
    }
}
