package fr.uga.l3miage.integrator.cyberCommandes.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddEmployeIdTourneeRequest {
    private String idEmploye;
}
