package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.LivraisonMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivraisonService {
    private final LivraisonComponent livraisonComponent;
    private final LivraisonMapper livraisonMapper;
    public LivraisonResponseDTO createLivraison(LivraisonCreationRequest livraisonCreationRequest){
        return null;
    }


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

}
