package fr.uga.l3miage.integrator.cyberRessources.response;

import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Représente un Employé")
public class EmployeResponseDTO {
    @Schema(description = "Trigramme de l'employe")
    private String trigramme;

    @Schema(description = "L'adresse mail de l'employe")
    private String email;

    @Schema(description = "Le prenom de l'employe")
    private String prenom;

    @Schema(description = "Le nom de l'employe")
    private String nom;

    @Schema(description = "La photo de l'employe")
    private String photo;

    @Schema(description = "Le numéro de telephone de l'employe")
    private String telephone;

    @Schema(description = "L'emploi de l'employe")
    private Emploi emploi;

    @Schema(description = "l'entrepot de l'employe")
    private EntrepotResponseDetailDTO entrepot;
}
