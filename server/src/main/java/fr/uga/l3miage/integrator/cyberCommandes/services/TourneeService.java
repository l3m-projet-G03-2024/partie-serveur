package fr.uga.l3miage.integrator.cyberCommandes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;

    public List<TourneeResponseDTO> getTourneesByEtats(EtatsDeTournee etatsDeTournee){
        try {
            List<TourneeEntity> tourneeEntities = (etatsDeTournee == null) ? 
            tourneeComponent.findAllTournee() : tourneeComponent.findAllTourneesByEtat(etatsDeTournee);
            return tourneeMapper.toTourneeResponseDTO(tourneeEntities);
        } catch (Exception e) {
            throw new NotFoundEntityRestException(e.getMessage());
        }

    }

}
