package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;


public class ConflictWithRessourceRestException extends RuntimeException{
    public ConflictWithRessourceRestException(String message) {
        super(message) ;
    }
}
