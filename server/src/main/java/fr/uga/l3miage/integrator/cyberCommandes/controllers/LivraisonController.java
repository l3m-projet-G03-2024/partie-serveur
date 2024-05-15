package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.endpoints.LivraisonEndPoints;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonsCreationTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonUpdateResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.services.LivraisonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class LivraisonController implements LivraisonEndPoints {
    private final LivraisonService livraisonService;

    @Override
    public List<LivraisonResponseDTO> getLivraisons(EtatsDeLivraison etat) {

        return livraisonService.getLivraisons(etat); 
    }

    @Override
    public LivraisonCreationResponseDTO createLivraisons(LivraisonsCreationTourneeRequest livraisonsCreationTourneeRequest) {
        return livraisonService.createLivraisons(livraisonsCreationTourneeRequest);
    }

    @Override
    public LivraisonUpdateResponseDTO updateLivraison(String livraison, LivraisonUpdateRequest livraisonUpdateRequest){
        return livraisonService.updateLivraison(livraison, livraisonUpdateRequest);
    }

    @Override
    public LivraisonResponseDTO getLivraisonDetailCommande(String referenceLivraison) {
        return livraisonService.getLivraisonsByCommandes(referenceLivraison);
    }

}
