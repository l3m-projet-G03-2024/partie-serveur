package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.services.JourneeService;
import fr.uga.l3miage.integrator.cyberCommandes.endpoints.JourneeEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JourneeController implements JourneeEndPoints {
    private final JourneeService journeeService;

    public List<JourneeResponseDTO> getAllJournees(){
        return journeeService.getAllJournees();
    }

    public void deleteJourneeById(String reference){
        journeeService.deleteJourneeById(reference);
    }

    
    public JourneeResponseDTO createJournee(JourneeRequest journeeRequest){
        return journeeService.createJournee(journeeRequest) ;
    }

    
     public JourneeDetailResponseDTO getJourneeById(String reference) {
         return journeeService.getJournee(reference);
     }
}
