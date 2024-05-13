package fr.uga.l3miage.integrator.cyberRessources.exceptions.rest;

import fr.uga.l3miage.integrator.cyberRessources.errors.EmployeNotFoundErrorResponse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeNotFoundRestException extends RuntimeException {
    private String emailEmploye;

    public EmployeNotFoundRestException(String message, String emailEmploye) {
        super(message);
        this.emailEmploye = emailEmploye;
    }


}
