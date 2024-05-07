package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

import lombok.Getter;

@Getter
public class CamionNotFoundException extends Exception{
    public CamionNotFoundException(String message) {
        super(message);
    }
}
