package fr.uga.l3miage.integrator.cyberCommandes.request;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "corps de requete de création des tournées d'une journée")
public class TourneeCreationRequest {

    @Schema(description = "reference de la tournée à créer")
    private final String reference;
    @Schema(description = "etat de la tournée à créer")
    private final EtatsDeTournee etat;
    @Schema(description = "distance totale de la tournée")
    private final double distance;
}
