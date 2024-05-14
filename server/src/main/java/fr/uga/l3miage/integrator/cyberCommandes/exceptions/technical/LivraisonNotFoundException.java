package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class LivraisonNotFoundException extends Exception{
    public LivraisonNotFoundException(String message){
        super(message);
    }
}
