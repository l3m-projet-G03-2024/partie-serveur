package fr.uga.l3miage.integrator.cyberVitrine.errors.technical;

public class CommandesNotFoundException extends RuntimeException{
    public CommandesNotFoundException(String message) {
        super(message);
    }
}
