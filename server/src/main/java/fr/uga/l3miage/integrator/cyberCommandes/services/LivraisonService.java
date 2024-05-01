package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;

import lombok.RequiredArgsConstructor;
import java.util.ArrayList;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivraisonService {
    private final LivraisonComponent livraisonComponent;
    private final LivraisonMapper livraisonMapper;
    private final TourneeRepository tourneeRepository;

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

    public LivraisonCreationResponseDTO createLivraisons(LivraisonsCreationTourneeRequest livraisons){
        try {
            List<LivraisonEntity> livraisonEntities = new ArrayList<>();

            for (LivraisonTourneeRequest livraison : livraisons.getLivraisons()) {
                TourneeEntity tourneeEntity = tourneeRepository.findById(livraison.getReferenceTournee()).orElseThrow();
                LivraisonEntity livraisonEntity = livraisonMapper.toEntity(livraison);
                livraisonEntity.setTourneeEntity(tourneeEntity);
                livraisonEntities.add(livraisonEntity);
            }
            livraisonComponent.createLivraisons(livraisonEntities);
            return new LivraisonCreationResponseDTO(true,"Livraions créé(s) avec succès");
        } catch (Exception e) {
             throw new BadRequestRestException(e.getMessage());
        }


    }

}
