package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class JourneeNotFoundException extends Exception {
    public JourneeNotFoundException(String message){
        super(message);
    }
}
