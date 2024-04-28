package fr.uga.l3miage.integrator.cyberCommandes.request;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourneeCreationRequest {

    private final String reference;
    private final EtatsDeTournee etat;
    private final double distance;
}
