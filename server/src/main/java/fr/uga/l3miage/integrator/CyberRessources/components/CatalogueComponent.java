package fr.uga.l3miage.integrator.CyberRessources.components;

import fr.uga.l3miage.integrator.CyberRessources.repositories.CatalogueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogueComponent {
    private final CatalogueRepository catalogueRepository;
}
