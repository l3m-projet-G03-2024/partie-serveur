package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.TourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonEntityNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        LocalDateTime creationDate = LocalDateTime.now();

        livraisons.getLivraisons().stream()
            .map(livraison -> {
                TourneeEntity tourneeEntity;
                LivraisonEntity livraisonEntity = livraisonMapper.toEntity(livraison);
                livraisonEntity.setDate(creationDate);
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
        return new LivraisonCreationResponseDTO(true, "Livraisons créées avec succès");
}

    
}
