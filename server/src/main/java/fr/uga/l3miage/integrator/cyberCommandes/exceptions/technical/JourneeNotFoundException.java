package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class JourneeNotFoundException extends Exception {

    private final String reference;
    public JourneeNotFoundException(String message,String reference){
        super(message);

        this.reference = reference;
    }


}
