package fr.uga.l3miage.integrator.cyberCommandes.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddEmployeIdTourneeRequest {
    @Schema(description = "trigramme d'employe")
    private String idEmploye;

    public AddEmployeIdTourneeRequest() {

    }

    public AddEmployeIdTourneeRequest (String idEmploye) {
        this.idEmploye = idEmploye;
    }
}
