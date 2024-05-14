package fr.uga.l3miage.integrator.cyberCommandes.request;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "corps de requete de création des tournées d'une journée")
public class TourneeCreationRequest {

    @Schema(description = "reference de la tournée à créer")
    private String reference;
    @Schema(description = "etat de la tournée à créer")
    private EtatsDeTournee etat;
    @Schema(description = "distance totale de la tournée")
    private double distance;
}
