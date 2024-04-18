package fr.uga.l3miage.integrator.CyberRessources.components;

import fr.uga.l3miage.integrator.CyberRessources.repositories.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrepotComponent {
    private final EntrepotRepository entrepotRepository;
}
