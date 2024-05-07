package fr.uga.l3miage.integrator.cyberProduit.errors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreationFailedErrorResponse {
    private final String uri;
    private final String errorMessage;
}
