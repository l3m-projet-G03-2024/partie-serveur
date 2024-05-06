package fr.uga.l3miage.integrator.cyberProduit.components;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EntrepotComponent {
    private final EntrepotRepository entrepotRepository;

    public List<EntrepotEntity> getAllEntrepots(){
        return entrepotRepository.findAll();
    }
    public EntrepotEntity getEntrepotByNom(String nom){
        return entrepotRepository.findById(nom).orElseThrow(() -> null);
    }
}
