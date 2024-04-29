package fr.uga.l3miage.integrator.cyberCommandes.request;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeUpdateRequestDTO {
    private final EtatsDeJournee etat;
    private final LocalDate date;
    private final Double distanceAParcourir;
    private final Double montant;
    private final Integer tdmTheorique;
}
