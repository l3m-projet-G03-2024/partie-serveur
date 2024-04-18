package fr.uga.l3miage.integrator.cyberVitrine.components;

import fr.uga.l3miage.integrator.cyberVitrine.repositories.LigneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LigneComponent {
    private final LigneRepository ligneRepository ;
}
