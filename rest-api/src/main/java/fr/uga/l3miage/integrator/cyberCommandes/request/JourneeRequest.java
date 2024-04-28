package fr.uga.l3miage.integrator.cyberCommandes.request;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class JourneeRequest {
    private final String reference ;
    private final LocalDate date ; // incohérence avec JourneeEntity
}
