package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;

@Getter
public class EmployeRestException extends RuntimeException{
    private final String emailEmploye;
    public EmployeRestException(String message, String emailEmploye){
        super(message);
        this.emailEmploye = emailEmploye;
    }
}
