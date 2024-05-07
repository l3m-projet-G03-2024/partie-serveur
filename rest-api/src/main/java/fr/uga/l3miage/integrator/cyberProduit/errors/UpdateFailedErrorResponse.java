package fr.uga.l3miage.integrator.cyberProduit.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateFailedErrorResponse {
    private final String uri;
    private final String errorMessage;
}
