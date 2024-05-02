package fr.uga.l3miage.integrator.cyberRessources.controllers;

import fr.uga.l3miage.integrator.cyberRessources.endpoints.CamionEnpoints;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.services.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CamionController implements CamionEnpoints {
    private final CamionService camionService;
    @Override
    public List<CamionResponseDTO> getCamionsByIdEntrpot(String id) {
        return camionService.getCamions(id);
    }

}
