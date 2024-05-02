package fr.uga.l3miage.integrator.cyberRessources.services;

import fr.uga.l3miage.integrator.cyberRessources.components.CamionComponent;
import fr.uga.l3miage.integrator.cyberRessources.mappers.CamionMapper;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CamionService {
    private final CamionComponent camionComponent;
    private final CamionMapper camionMapper;

    public List<CamionResponseDTO> getCamions(String idEntrepot) {
        List<CamionEntity>  listCamions = camionComponent.findAllCamionByIdEntrepot(idEntrepot);
        return camionMapper.toCamionResponseDTO(listCamions);
    }

}
