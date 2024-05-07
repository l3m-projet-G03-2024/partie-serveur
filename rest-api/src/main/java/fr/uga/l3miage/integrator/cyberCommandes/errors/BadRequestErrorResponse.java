package fr.uga.l3miage.integrator.cyberCommandes.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequestErrorResponse {
    private final String uri;
    private final String errorMessage;
}
