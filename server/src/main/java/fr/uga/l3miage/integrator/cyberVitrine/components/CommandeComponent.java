package fr.uga.l3miage.integrator.cyberVitrine.components;

import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandeComponent {
    private final CommandeRepository commandeRepository ;
}
