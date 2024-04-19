package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
<<<<<<< HEAD
import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
=======
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
>>>>>>> 19a9ad5 (feat: implementation de delete par reference)
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneeService {
    private final JourneeComponent journeeComponent;
<<<<<<< HEAD
    private final TourneeComponent tourneeComponent ;
    private final JourneeMapper journeeMapper ;
=======
    private final JourneeMapper journeeMapper;
>>>>>>> 19a9ad5 (feat: implementation de delete par reference)

    public List<JourneeResponseDTO> getAllJournees(){
        List<JourneeEntity> journeeEntities = journeeComponent.findAllJournees();
        List<JourneeResponseDTO> journeeResponseDTOS = new ArrayList<>();
        journeeResponseDTOS = journeeMapper.toJourneeResponseDTOS(journeeEntities);
        return journeeResponseDTOS;
    }

    public void deleteJourneeById(String reference){
        journeeComponent.deleteJourneeById(reference);
    }

    public JourneeResponseDTO createJournee(JourneeRequest journeeRequest){
        try{
            JourneeEntity journeeEntity = journeeMapper.toEntity(journeeRequest) ;
            journeeEntity.setEtat(EtatsDeJournee.NONPLANIFIEE) ;
            return journeeMapper.toJourneeResponseDTO(journeeComponent.createJourneeEntity(journeeEntity)) ;
        } catch (Exception e) {
            throw new BadRequestRestException(e.getMessage()) ;
        }
    }
}
