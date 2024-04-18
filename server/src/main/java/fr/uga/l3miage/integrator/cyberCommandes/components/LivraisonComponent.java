package fr.uga.l3miage.integrator.cyberCommandes.components;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LivraisonComponent {
    private final LivraisonRepository livraisonRepository;
}

