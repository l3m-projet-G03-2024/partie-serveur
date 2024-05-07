package fr.uga.l3miage.integrator.cyberVitrine.components;


import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.technical.CommandeEntityNotFoundException;
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

    public CommandeEntity getCommandeByReference(String reference) throws CommandeEntityNotFoundException {
        return commandeRepository.findById(reference)
                .orElseThrow(() -> new CommandeEntityNotFoundException(String.format(reference, "La commande [%s] n'a pas été trouvée", reference))) ;
    }

    public List<CommandeEntity> updateCommandes(List<CommandeEntity> commandeEntity) {
        return commandeRepository.saveAll(commandeEntity) ;
    }


}
