package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.services.JourneeService;
import fr.uga.l3miage.integrator.endpoints.JourneeEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import fr.uga.l3miage.integrator.response.JourneeResponseDTO;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class JourneeController implements JourneeEndPoints {
    private final JourneeService journeeService;

    @Override
    public List<JourneeResponseDTO> getAllJournees(){
        return null;
        //return journeeService.getAllJournee();
    }
}
