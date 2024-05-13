package fr.uga.l3miage.integrator.cyberVitrine.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonNotFoundException;
import fr.uga.l3miage.integrator.cyberProduit.mappers.ProduitMapper;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.technical.CommandeEntityNotFoundException;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingBodyRequest;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.components.CommandeComponent;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.response.DetailsCommandeResponseDTO;
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
    private final ProduitMapper produitMapper;

    public List<CommandeResponseDTO> getCommandes(EtatsDeCommande etat) {
        List<CommandeEntity> commandeEntities = etat==null ?
                commandeComponent.findAllCommandes() :
                commandeComponent.findCommandByEtat(etat);
        return commandeMapper.toCommandesResponseDTO(commandeEntities);
    }

    public List<CommandeResponseDTO> updateCommandes(CommandeUpdatingBodyRequest commandes) {
        try {
            List<CommandeEntity> commandeEntities = new ArrayList<>();
            for (CommandeUpdatingRequest commandeUpdatingRequest : commandes.getCommandes()) {
                CommandeEntity commandeEntity = commandeComponent.getCommandeByReference(commandeUpdatingRequest.getReference()) ;
                commandeMapper.updateCommandeFromDTO(commandeUpdatingRequest, commandeEntity);
                commandeEntity.setLivraisonEntity(livraisonComponent.getLivraisonByReference(commandeUpdatingRequest.getReferenceLivraison()));
                commandeEntities.add(commandeEntity);
            }
            List<CommandeEntity> commandeUpdates = commandeComponent.updateCommandes(commandeEntities);

            return commandeMapper.toCommandesResponseDTO(commandeUpdates);
        } catch (CommandeEntityNotFoundException | LivraisonNotFoundException e) {
            throw new BadRequestRestException(e.getMessage()) ;
        }
    }

    public DetailsCommandeResponseDTO getDetailsCommande(String referenceCommande) {
        try {
            CommandeEntity commande = commandeComponent.getCommandeByReference(referenceCommande);
            return commandeMapper.toDetailsCommandeResponseDTO(commande);
        } catch (CommandeEntityNotFoundException e) {
            throw new BadRequestRestException(e.getMessage());
        }
    }


}
