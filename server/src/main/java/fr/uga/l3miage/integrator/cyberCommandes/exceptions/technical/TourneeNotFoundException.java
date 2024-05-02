package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class TourneeNotFoundException  extends Exception {

    private final  String  reference;
    public TourneeNotFoundException( String message, String reference){
        super(message);
        this.reference = reference;
    }

}
