package fr.uga.l3miage.integrator.cyberProduit.components;

import fr.uga.l3miage.integrator.cyberProduit.repositories.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockComponent {
    private final StockRepository stockRepository;
}
