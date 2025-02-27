package fr.uga.l3miage.integrator.cyberCommandes.controllers;


import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;


import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.services.JourneeService;
import fr.uga.l3miage.integrator.cyberCommandes.endpoints.JourneeEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JourneeController implements JourneeEndPoints {
    private final JourneeService journeeService;
    @Override
    public List<JourneeDetailResponseDTO> findAllJournees(){
        return journeeService.findAllJournees();
    }
    @Override
    public void deleteJourneeById(String reference){
        journeeService.deleteJourneeById(reference);
    }

    @Override
    public JourneeDetailResponseDTO createJournee(JourneeCreationRequest journeeRequest){
        return journeeService.createJournee(journeeRequest) ;
    }

     @Override
     public JourneeDetailResponseDTO getJourneeById(String reference) {
         return journeeService.getJournee(reference);
     }

    @Override
    public JourneeDetailResponseDTO updateJournee(String reference, JourneeUpdateRequest journeeUpdate){
        return journeeService.updateJournee(reference, journeeUpdate);
    }
}
