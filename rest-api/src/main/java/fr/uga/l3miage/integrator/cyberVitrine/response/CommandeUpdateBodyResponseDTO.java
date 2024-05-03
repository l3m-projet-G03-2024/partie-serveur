package fr.uga.l3miage.integrator.cyberVitrine.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommandeUpdateBodyResponseDTO {
    private List<CommandeResponseDTO> commandes;
}
