package fr.uga.l3miage.integrator.cyberProduit.components;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EntrepotComponent {
    private final EntrepotRepository entrepotRepository;

    public EntrepotEntity getEntrepotByNom(String nom){
        return entrepotRepository.findById(nom).orElseThrow(() -> null);
    }
}
