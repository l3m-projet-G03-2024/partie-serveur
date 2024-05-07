package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import java.util.List;
import java.util.Set;


import fr.uga.l3miage.integrator.cyberCommandes.request.AddEmployeIdTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.CamionImmatriculationTouneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.TourneesCreationBodyRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeCreationResponseDTO;
import org.springframework.web.bind.annotation.RestController;

import fr.uga.l3miage.integrator.cyberCommandes.endpoints.TourneeEndPoints;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.services.TourneeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class TourneeController implements TourneeEndPoints {
    private final TourneeService tourneeService;
    
    @Override
    public List<TourneeResponseDTO> getAllTournees(EtatsDeTournee etatsDeTournee, String reference) {
        return tourneeService.getTourneesByEtatsOrReferenceJournee(etatsDeTournee,reference);

    }

    @Override
    public TourneeCreationResponseDTO createTournees(TourneesCreationBodyRequest tourneeCreationBodyRequest) {
        return tourneeService.createTournees(tourneeCreationBodyRequest);
    }

    @Override
    public TourneeResponseDTO addEmployeInTournee(String referenceTournee, AddEmployeIdTourneeRequest addEmployeIdTourneeRequest){
        return tourneeService.addEmployeInTournee(referenceTournee, addEmployeIdTourneeRequest.getIdEmploye());
    }

    @Override
    public TourneeResponseDTO getTournee(String referenceTournee) {
        return tourneeService.getTournee(referenceTournee);
    }
    @Override
    public Set<TourneeResponseDTO> getAllTourneesByEmployeEmail(String emailEmploye){
        return tourneeService.getAllTourneesByEmployeEmail(emailEmploye);

    }

    public AddCamionOnTourneeResponseDTO addCamionOnTournee(String reference, CamionImmatriculationTouneeRequest camionImmatriculationTouneeRequest) {
        return tourneeService.addCamionOnTournee(reference, camionImmatriculationTouneeRequest);

    }
}
