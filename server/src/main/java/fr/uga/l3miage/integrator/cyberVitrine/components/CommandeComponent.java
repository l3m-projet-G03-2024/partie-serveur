package fr.uga.l3miage.integrator.cyberVitrine.components;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandeComponent {
    private final CommandeRepository commandeRepository ;

    public List<CommandeEntity> findAllCommandes() {
        return commandeRepository.findAll();
    }

    public List<CommandeEntity> findCommandByEtat(EtatsDeCommande etatDeCommande) {
        return commandeRepository.findAllByEtat(etatDeCommande);
    }

}
