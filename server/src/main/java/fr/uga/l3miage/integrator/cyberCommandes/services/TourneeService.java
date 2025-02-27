package fr.uga.l3miage.integrator.cyberCommandes.services;

import java.util.*;
import java.util.stream.Collectors;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.components.LivraisonComponent;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.NotFoundRestException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import fr.uga.l3miage.integrator.cyberCommandes.request.CamionImmatriculationTouneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeUpdateLivraisonRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.UpdatingEtatAndTdrEffectifOfTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import fr.uga.l3miage.integrator.cyberRessources.components.EmployeComponent;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.rest.EmployeNotFoundRestException;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.CamionRepository;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import org.springframework.stereotype.Service;

import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.TourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;

import lombok.AllArgsConstructor;

import javax.swing.plaf.IconUIResource;

@Service
@AllArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final JourneeComponent journeeComponent;
    private final EmployeComponent employeComponent;
    private final CamionRepository camionRepository;
    private final LivraisonRepository livraisonRepository;


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
            throw new NotFoundRestException(e.getMessage());
        }
    }

    public TourneeResponseDTO addEmployeInTournee(String referenceTournee, String idEmploye){
        try{
            TourneeEntity tourneeEntity = tourneeComponent.findTourneeByReference(referenceTournee);
            EmployeEntity employeEntity = employeComponent.findEmployeByReference(idEmploye);

            Set<TourneeEntity> tourneeEntities = tourneeEntity.getJournee().getTournees();

            Optional<TourneeEntity> tourneeToDeleteEmploye =  tourneeEntities.stream().filter(tournee ->
                    tournee.getEmployes().stream().anyMatch(employe -> employe.getTrigramme().equals(idEmploye))
            ).findAny();

            if (tourneeToDeleteEmploye.isPresent()) {
                TourneeEntity tournee = tourneeToDeleteEmploye.get();
                tournee.getEmployes().remove(employeEntity);
                employeEntity.getTourneeEntities().remove(tournee);
                tourneeComponent.addEmployeInTournee(tournee);
            }

            if (tourneeEntity.getEmployes() == null) {
                tourneeEntity.setEmployes(new HashSet<>());
            }

            tourneeEntity.getEmployes().add(employeEntity);

            if (employeEntity.getTourneeEntities() == null) {
                employeEntity.setTourneeEntities(new HashSet<>());
            }

            employeEntity.getTourneeEntities().add(tourneeEntity);
            return tourneeMapper.toTourneeResponseDTO(tourneeComponent.addEmployeInTournee(tourneeEntity));
        } catch (TourneeNotFoundException e) {
            throw new EmployeNotFoundRestException(e.getMessage(),idEmploye);
        }
    }


    public void changeLivraisonOnTournee(TourneeUpdateLivraisonRequest updateRequest) {
        try {
            TourneeEntity newTournee        = tourneeComponent.findTourneeByReference(updateRequest.getNewReferenceTournee());
            LivraisonEntity livraisonExist  = livraisonRepository.findById(updateRequest.getReferenceLivraison()).orElseThrow(() -> new NotFoundRestException("livraison non trouvee"));
            TourneeEntity currentTournee    = livraisonExist.getTourneeEntity();

            if(currentTournee.getReference().equals(updateRequest.getNewReferenceTournee())){
                sameTournee(livraisonExist, updateRequest.getNewOrdre(), updateRequest.getNewTourneeDistance());
            }else {
                currentTournee.getLivraisons().remove(livraisonExist);
                currentTournee.setDistance(updateRequest.getOldTourneeDistance());

                currentTournee.getLivraisons().forEach(livraison -> {
                    if (livraison.getOrdre() > livraisonExist.getOrdre()) {
                        livraison.setOrdre(livraison.getOrdre() - 1);
                        livraisonRepository.save(livraison);
                    }
                });
                tourneeComponent.saveTournee(currentTournee);
                newTournee.getLivraisons().forEach(livraison -> {
                    if (livraison.getOrdre() >= updateRequest.getNewOrdre()) {
                        livraison.setOrdre(livraison.getOrdre() + 1);
                        livraisonRepository.save(livraison);
                    }
                });

                livraisonExist.setOrdre(updateRequest.getNewOrdre());
                livraisonExist.setTourneeEntity(newTournee);
                newTournee.getLivraisons().add(livraisonExist);
                newTournee.setDistance(updateRequest.getNewTourneeDistance());
                livraisonRepository.save(livraisonExist);
                tourneeComponent.saveTournee(newTournee);
            }
        }catch (TourneeNotFoundException e) {
            throw new NotFoundRestException(e.getMessage());
        }

    }

    public void sameTournee(LivraisonEntity currentLivraison, int newOrdre, double newTourneeDistance){
        TourneeEntity currentTournee = currentLivraison.getTourneeEntity();
        int oldOrdre = currentLivraison.getOrdre();

        currentTournee.getLivraisons().forEach(livraison -> {
            if (oldOrdre < newOrdre && livraison.getOrdre() > oldOrdre && livraison.getOrdre() <= newOrdre) {
                livraison.setOrdre(livraison.getOrdre() - 1);
                livraisonRepository.save(livraison);
            } else if (oldOrdre > newOrdre && livraison.getOrdre() < oldOrdre && livraison.getOrdre() >= newOrdre) {
                livraison.setOrdre(livraison.getOrdre() + 1);
                livraisonRepository.save(livraison);
            }
        });

        currentLivraison.setOrdre(newOrdre);
        currentTournee.setDistance(newTourneeDistance);
        livraisonRepository.save(currentLivraison);
        tourneeComponent.saveTournee(currentTournee);
    }

    public Set<TourneeResponseDTO> getAllTourneesByEmployeEmail(String emailEmploye){
        try{
            Set<TourneeEntity> tourneeEntities = new HashSet<>();
            if(emailEmploye != null){
                tourneeEntities = tourneeComponent.getAllTourneesByEmployeEmail(emailEmploye);
            }
            return tourneeEntities.stream()
                    .map(tourneeMapper::toTourneeResponseDTO)
                    .collect(Collectors.toSet());
        } catch (NotFoundEmployeEntityException e) {
            throw new EmployeNotFoundRestException(e.getMessage(), emailEmploye);
        }
    }

    public TourneeResponseDTO getTournee(String referenceTournee) {
        try {
            TourneeEntity tourneeEntity = tourneeComponent.findTourneeByReference(referenceTournee) ;
            return tourneeMapper.toTourneeResponseDTO(tourneeEntity) ;
        } catch (TourneeNotFoundException e) {
            throw new NotFoundRestException(e.getMessage(), referenceTournee) ;
        }
    }

    public AddCamionOnTourneeResponseDTO addCamionOnTournee(String referenceTounee , CamionImmatriculationTouneeRequest camionImmatriculationTouneeRequest) {
        try {
            TourneeEntity tourneeEntity = tourneeComponent.findTourneeByReference(referenceTounee);
            CamionEntity camionEntity = camionRepository.findById(camionImmatriculationTouneeRequest.getImmatriculation())
                    .orElseThrow(() -> new NotFoundRestException(String.format("Camion non trouve %s", camionImmatriculationTouneeRequest.getImmatriculation())) );
            tourneeEntity.setCamion(camionEntity);
            return tourneeMapper.toTourneeCamionResponseDTO(tourneeComponent.saveTournee(tourneeEntity));
        } catch (TourneeNotFoundException e){
            throw new NotFoundRestException(e.getMessage());
        }
    }

    public TourneeResponseDTO updateEtatAndTdrEffectifOfTournee(String refTournee, UpdatingEtatAndTdrEffectifOfTourneeRequest updatingEtatAndTdrEffectifOfTourneeRequest) {
        try {
            TourneeEntity tourneeEntity = tourneeComponent.findTourneeByReference(refTournee) ;
            tourneeMapper.updateTourneeEntityFromRequest(updatingEtatAndTdrEffectifOfTourneeRequest, tourneeEntity);
            tourneeComponent.saveTournee(tourneeEntity) ;
            return tourneeMapper.toTourneeResponseDTO(tourneeEntity) ;
        } catch (TourneeNotFoundException e) {
            throw new NotFoundRestException(e.getMessage());
        }
    }
}
