package fr.uga.l3miage.integrator.CyberRessources.components;

import fr.uga.l3miage.integrator.CyberRessources.repositories.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduitComponent {
    private final ProduitRepository produitRepository;
}
