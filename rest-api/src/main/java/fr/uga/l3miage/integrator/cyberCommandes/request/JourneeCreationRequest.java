package fr.uga.l3miage.integrator.cyberCommandes.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeCreationRequest {
    private final String reference ;
    private final LocalDate date ; // incohérence avec JourneeEntity
}
