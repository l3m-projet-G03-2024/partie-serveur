package fr.uga.l3miage.integrator.CyberRessources.components;

import fr.uga.l3miage.integrator.CyberRessources.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockComponent {
    private final StockRepository stockRepository;
}
