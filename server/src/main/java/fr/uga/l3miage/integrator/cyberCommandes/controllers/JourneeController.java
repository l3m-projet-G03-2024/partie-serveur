package fr.uga.l3miage.integrator.cyberCommandes.controllers;

import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.services.JourneeService;
import fr.uga.l3miage.integrator.cyberCommandes.endpoints.JourneeEndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class JourneeController implements JourneeEndPoints {
    private final JourneeService journeeService;
    private final JourneeMapper journeeMapper;

    public List<JourneeResponseDTO> getAllJournees(){
        List<JourneeEntity> journeeEntities = journeeService.getAllJournees();
        List<JourneeResponseDTO> journeeResponseDTOS = new ArrayList<>();

        journeeResponseDTOS = journeeMapper.toJourneeResponseDTOS(journeeEntities);

        return journeeResponseDTOS;
    }

    @Override
    public JourneeResponseDTO createJournee(JourneeRequest journeeRequest){
        return journeeService.createJournee(journeeRequest) ;
    }

}
