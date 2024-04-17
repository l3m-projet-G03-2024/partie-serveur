package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CamionComponent {
    private final CamionRepository camionRepository;
}
