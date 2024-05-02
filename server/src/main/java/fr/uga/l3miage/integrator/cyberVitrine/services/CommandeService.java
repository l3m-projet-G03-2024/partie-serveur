package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.errors.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService {
    private final CommandeComponent commandeComponent ;
    private final CommandeMapper commandeMapper;
    private final LivraisonComponent livraisonComponent ;

    public List<CommandeResponseDTO> getCommandes(EtatsDeCommande etat) {
        List<CommandeEntity> commandeEntities = etat==null ?
                commandeComponent.findAllCommandes() :
                commandeComponent.findCommandByEtat(etat);
        return commandeMapper.toCommandesResponseDTO(commandeEntities);
    }

    public List<CommandeResponseDTO> updateCommandes(List<CommandeUpdatingRequest> commandes) {
        try {
            List<CommandeResponseDTO> crd = new ArrayList<>();
            for (CommandeUpdatingRequest cur : commandes) {
                CommandeEntity ce = commandeComponent.getCommandeByReference(cur.getReference()) ;
                ce.setLivraisonEntity(livraisonComponent.getLivraisonByReference(cur.getReferenceLivraison()));
                commandeMapper.updateCommandeFromDTO(cur, ce);
                crd.add(commandeMapper.toCommandeResponseDTO(ce)) ;
            }
            return crd ;
        } catch (Exception e) {
            throw new BadRequestRestException(e.getMessage()) ;
        }
    }


}
