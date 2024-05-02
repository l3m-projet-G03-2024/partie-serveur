package fr.uga.l3miage.integrator.cyberVitrine.errors.technical;

public class CommandeEntityNotFoundException extends Exception {
    public CommandeEntityNotFoundException(String format){
        super(format);
    }
}
