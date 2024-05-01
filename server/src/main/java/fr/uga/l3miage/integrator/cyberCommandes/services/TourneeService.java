package fr.uga.l3miage.integrator.cyberCommandes.services;

import java.util.ArrayList;
import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import org.springframework.stereotype.Service;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundEntityRestException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final JourneeRepository journeeRepository;

    public List<TourneeResponseDTO> getTourneesByEtatsOrReferenceJournee(EtatsDeTournee etatsDeTournee,String referenceJournee){
        try {

            List<TourneeEntity> tourneeEntities = null;

            if (etatsDeTournee != null || referenceJournee != null ) {
                tourneeEntities = tourneeComponent.findAllTourneesByEtatOrReferenceJournee(etatsDeTournee, referenceJournee);
                System.out.println("STEP 1 CA MARCHE");

            }
            if (etatsDeTournee == null && referenceJournee == null ){
                tourneeEntities = tourneeComponent.findAllTournee();
                System.out.println("STEP 2 CA MARCHE");
            }

            return tourneeMapper.toTourneeResponseDTO(tourneeEntities);

        } catch (Exception e) {
            throw new NotFoundEntityRestException(e.getMessage());
        }

    }
    public TourneeCreationResponseDTO createTournee(List<TourneeCreationRequest> tournees,String refJournee) {
        try {
            List<TourneeEntity> tourneeEntities = new ArrayList<>();
            JourneeEntity journee = journeeRepository.findByReference(refJournee);
            if (journee == null) {
                new TourneeCreationResponseDTO(false,"Journée avec la réference "+refJournee+" non trouvée");
            }
            for (TourneeCreationRequest tourneeCreationRequest : tournees) {
                TourneeEntity tourneeEntity = tourneeMapper.toEntity(tourneeCreationRequest);
                tourneeEntity.setJournee(journee);
                tourneeEntities.add(tourneeEntity);

            }
            tourneeComponent.createTournees(tourneeEntities);

            return new TourneeCreationResponseDTO(true,"Toutes les tournées ont été créés avec succès");
        }
        catch (Exception e) {
            return new TourneeCreationResponseDTO(false,"Erreur lors de la création des tournées: " + e.getMessage());
        }

    }

   
}




