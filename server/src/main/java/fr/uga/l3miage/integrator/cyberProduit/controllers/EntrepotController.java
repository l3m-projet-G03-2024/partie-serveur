package fr.uga.l3miage.integrator.cyberProduit.controllers;

import fr.uga.l3miage.integrator.cyberProduit.endpoints.EntrepotEndPoints;
import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import fr.uga.l3miage.integrator.cyberProduit.services.EntrepotService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class EntrepotController implements EntrepotEndPoints {
    private final EntrepotService entrepotService;
    @Override
    public List<EntrepotResponseDetailDTO> getAllEntrepots(){
        return entrepotService.getAllEntrepots();
    }
}
