package fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical;

public class LivraisonNotFoundException extends Exception{
    private final String reference;
    public LivraisonNotFoundException(String message, String reference)
    {
        super(message);
        this.reference = reference;
    }
}
