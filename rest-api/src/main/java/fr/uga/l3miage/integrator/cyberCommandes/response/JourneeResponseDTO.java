package fr.uga.l3miage.integrator.cyberCommandes.response;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class JourneeResponseDTO {
    private String reference;
    private LocalDate date;
    private EtatsDeJournee etat;
}
