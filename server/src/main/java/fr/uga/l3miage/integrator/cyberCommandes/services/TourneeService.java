package fr.uga.l3miage.integrator.cyberCommandes.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.EmployeRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.JourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.TourneeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.components.EmployeComponent;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
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
    private final EmployeComponent employeComponent;


    public List<TourneeResponseDTO> getTourneesByEtatsOrReferenceJournee(EtatsDeTournee etatsDeTournee,String referenceJournee){
        List<TourneeEntity> tourneeEntities = null;
        if (etatsDeTournee != null || referenceJournee != null ) {
            tourneeEntities = tourneeComponent.findAllTourneesByEtatOrReferenceJournee(etatsDeTournee, referenceJournee);
        }
        if (etatsDeTournee == null && referenceJournee == null ){
            tourneeEntities = tourneeComponent.findAllTournee();
        }
        return tourneeMapper.toTourneesResponseDTO(tourneeEntities);
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

    public TourneeResponseDTO addEmployeInTournee(String referenceTournee, String idEmploye){
        try{
            TourneeEntity tourneeEntity = tourneeComponent.addEmployeInTournee(referenceTournee, idEmploye);
            return tourneeMapper.toTourneeResponseDTO(tourneeEntity);
        } catch (NotFoundEmployeEntityException | TourneeNotFoundException e) {
            throw new EmployeRestException(e.getMessage());
        }
    }

    public Set<TourneeResponseDTO> getAllTourneesByEmployeId (String idEmploye){
        try{
            Set<TourneeEntity> tourneeEntities = new HashSet<>();
            if(idEmploye != null){
                tourneeEntities = tourneeComponent.getAllTourneesByEmployeId(idEmploye);
            }
            return tourneeEntities.stream()
                    .map(tourneeMapper::toTourneeResponseDTO)
                    .collect(Collectors.toSet());
        } catch (NotFoundEmployeEntityException e) {
            throw new EmployeRestException(e.getMessage());
        }
    }

    public TourneeResponseDTO getTournee(String referenceTournee) {
        try {
            TourneeEntity tourneeEntity = tourneeComponent.findTourneeByReference(referenceTournee) ;
            return tourneeMapper.toTourneeResponseDTO(tourneeEntity) ;
        } catch (TourneeNotFoundException e) {
            throw new TourneeNotFoundRestException(e.getMessage(), referenceTournee) ;
        }
    }
}
