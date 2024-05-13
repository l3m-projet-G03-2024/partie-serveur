package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class TourneeNotFoundException  extends Exception {
    public TourneeNotFoundException( String message){
        super(message);
    }

}
