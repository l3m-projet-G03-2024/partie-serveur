package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.TourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;




@Service
@RequiredArgsConstructor
public class LivraisonService {
    private final LivraisonComponent livraisonComponent;
    private final LivraisonMapper livraisonMapper;
    private final TourneeComponent tourneeComponent;
   /* public LivraisonResponseDTO createLivraison(LivraisonCreationRequest livraisonCreationRequest){
        return null;
    }
    */

    public List<LivraisonResponseDTO> getLivraisons(EtatsDeLivraison etat) {
        List<LivraisonEntity> livraisonEntities;
        if (etat==null) {
            livraisonEntities = livraisonComponent.findAllLivraisons();
        }

        else {
            livraisonEntities = livraisonComponent.findLivraisonByEtat(etat);
        }
        return livraisonMapper.toLivraisonResponse(livraisonEntities);
    }
    
    public LivraisonCreationResponseDTO createLivraisons(LivraisonsCreationTourneeRequest livraisons) {
        List<LivraisonEntity> livraisonEntities = new ArrayList<>();

        livraisons.getLivraisons().stream()
            .map(livraison -> {
                TourneeEntity tourneeEntity;
                LivraisonEntity livraisonEntity = livraisonMapper.toEntity(livraison);
                try {
                    tourneeEntity = tourneeComponent.findTourneeByReference(livraison.getReferenceTournee());
                    livraisonEntity.setTourneeEntity(tourneeEntity);
                } catch (TourneeNotFoundException e) {
                    throw new TourneeNotFoundRestException(e.getMessage(), e.getReference());
                }
                return livraisonEntity;
            })
            .forEach(livraisonEntities::add); 

        livraisonComponent.createLivraisons(livraisonEntities);
        return new LivraisonCreationResponseDTO(true, "Livraisons crées avec succès");
}


    @SneakyThrows
    public LivraisonResponseDTO updateLivraison(String referenceLivraison, LivraisonUpdateRequest livraisonUpdateRequest){
        LivraisonEntity livraisonExist = livraisonComponent.getLivraisonByReference(referenceLivraison);
        livraisonMapper.updateLivraisonFromDTO(livraisonUpdateRequest, livraisonExist);
        if(livraisonUpdateRequest.getEtat().equals(EtatsDeLivraison.EFFECTUEE) || livraisonUpdateRequest.getEtat().equals(EtatsDeLivraison.ENPARCOURS)){
            Set<CommandeEntity> commandeEntities =  updateCommandesLinkedWithLivraison(livraisonExist.getCommandes(), livraisonUpdateRequest.getEtat());
            livraisonExist.setCommandes(commandeEntities);
        }
        return livraisonMapper.toLivraisonResponseDTO(livraisonComponent.updateLivraison(livraisonExist));
    }

    Set<CommandeEntity> updateCommandesLinkedWithLivraison(Set<CommandeEntity> commandeEntities, EtatsDeLivraison etatsDeLivraison){
        if(EtatsDeLivraison.EFFECTUEE.equals(etatsDeLivraison)){
            commandeEntities.forEach(commandeEntity -> commandeEntity.setEtat(EtatsDeCommande.LIVREE));
        }else{
            commandeEntities.forEach(commandeEntity -> commandeEntity.setEtat(EtatsDeCommande.ENLIVRAISON));
        }
        return commandeEntities;
    }
    public LivraisonResponseDTO getLivraisonByCommandes(String referenceLivraison)  {
       try {
           LivraisonEntity livraisonEntity = livraisonComponent.getLivraisonByReference(referenceLivraison);
           return livraisonMapper.toLivraisonResponseDTO(livraisonEntity);
       }catch (LivraisonNotFoundException e){
           throw  new NotFoundRestException(e.getMessage());
       }
    }
    
}
