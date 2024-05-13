package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class DeleteFailedRestException extends RuntimeException{
    private String reference;
    public DeleteFailedRestException(String message){
        super(message);
    }
    public DeleteFailedRestException(String message,String reference){
        super(message);
        this.reference = reference;
    }
}
