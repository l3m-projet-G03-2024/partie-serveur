package fr.uga.l3miage.integrator.cyberProduit.services;

import fr.uga.l3miage.integrator.cyberProduit.components.EntrepotComponent;
import fr.uga.l3miage.integrator.cyberProduit.mappers.EntrepotMapper;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EntrepotService {
    private final EntrepotComponent entrepotComponent;
    private final EntrepotMapper entrepotMapper;

    public List<EntrepotResponseDetailDTO> getAllEntrepots(){
        List<EntrepotEntity> entrepots = entrepotComponent.getAllEntrepots();
        return entrepotMapper.toEntrepotResponseDetailDTOS(entrepots);
    }

}
