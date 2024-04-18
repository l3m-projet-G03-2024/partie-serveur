package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeComponent commandeComponent ;
}
