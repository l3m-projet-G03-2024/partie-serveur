package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.endpoints.LivraisonEndPoints;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
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

}
