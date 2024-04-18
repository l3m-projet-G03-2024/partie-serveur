package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.response.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneeService {
    private final JourneeComponent journeeComponent;

    public List<JourneeResponseDTO> getAllJournees(){
        return null;
        //List<JourneeEntity> journeeEntities = journeeComponent.getAllJournees();
    }
}
