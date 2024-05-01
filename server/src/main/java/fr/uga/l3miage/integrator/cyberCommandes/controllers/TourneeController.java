package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
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
    public List<TourneeResponseDTO> getAllTournee(EtatsDeTournee etatsDeTournee,String reference) {
        return tourneeService.getTourneesByEtatsOrReferenceJournee(etatsDeTournee,reference);

    }

    @Override
    public TourneeCreationResponseDTO createTournee(List<TourneeCreationRequest> tourneeCreationRequests,String refJournee) {
        return tourneeService.createTournee(tourneeCreationRequests,refJournee);
    }

    


}
