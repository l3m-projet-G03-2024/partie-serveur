package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JourneeUpdateResponseDTO {
    String reference;
    EtatsDeJournee etat;
    LocalDate date;
    Double distanceAParcourir;
    Double montant;
    Integer tdmTheorique;
}
