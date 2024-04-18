package fr.uga.l3miage.integrator.cyberVitrine.components;

import fr.uga.l3miage.integrator.cyberVitrine.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientComponent {
    private final ClientRepository clientRepository ;
}
