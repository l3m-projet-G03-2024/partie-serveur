package fr.uga.l3miage.integrator.cyberCommandes.services;

import java.util.ArrayList;
import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.JourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import org.springframework.stereotype.Service;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final JourneeComponent journeeComponent;


    public List<TourneeResponseDTO> getTourneesByEtatsOrReferenceJournee(EtatsDeTournee etatsDeTournee,String referenceJournee){
        List<TourneeEntity> tourneeEntities = null;
        if (etatsDeTournee != null || referenceJournee != null ) {
            tourneeEntities = tourneeComponent.findAllTourneesByEtatOrReferenceJournee(etatsDeTournee, referenceJournee);
        }
        if (etatsDeTournee == null && referenceJournee == null ){
            tourneeEntities = tourneeComponent.findAllTournee();
        }
        return tourneeMapper.toTourneeResponseDTO(tourneeEntities);
    }


    public TourneeCreationResponseDTO createTournees(TourneesCreationBodyRequest tournees) {
        try {
            List<TourneeEntity> tourneeEntities = new ArrayList<>();
            JourneeEntity journee = journeeComponent.findJourneeByReference(tournees.getReferenceJournee());
            tournees.getTournees()
                    .stream()
                    .map(tourneeMapper::toEntity)
                    .peek(tournee -> tournee.setJournee(journee))
                    .forEach(tourneeEntities::add);

            tourneeComponent.createTournees(tourneeEntities);
            return new TourneeCreationResponseDTO(true,"Toutes les tournées ont été créés avec succès");
        }catch (JourneeNotFoundException e) {
            throw new JourneeNotFoundRestException(e.getMessage(),e.getReference());
        }
    }

}
