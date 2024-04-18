package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberVitrine.components.ClientComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientComponent clientComponent ;
}
