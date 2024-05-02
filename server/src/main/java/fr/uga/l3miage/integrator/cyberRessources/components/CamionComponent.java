package fr.uga.l3miage.integrator.cyberRessources.components;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.repositories.EntrepotRepository;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEntrepotEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CamionComponent {
    private final CamionRepository camionRepository;
    private final EntrepotRepository entrepotRepository;
    public List<CamionEntity> findAllCamionByIdEntrepot(String id) {
        return camionRepository.findByEntrepotNom(id);
    }

}
