package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeComponent commandeComponent ;
    private final CommandeMapper commandeMapper;

    public List<CommandeResponseDTO> getCommandes(EtatsDeCommande etat) {
        List<CommandeEntity> commandeEntities ;
        if (etat==null) {
            commandeEntities = commandeComponent.findAllCommandes();
        }
        else {

            commandeEntities = commandeComponent.findCommandByEtat(etat);
        }
        return commandeMapper.toCommandeResponseDTO(commandeEntities);
    }


}
