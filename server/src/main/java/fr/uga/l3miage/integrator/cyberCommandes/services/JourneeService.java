package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.components.TourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.BadRequestRestException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneeService {
    private final JourneeComponent journeeComponent;
    private final TourneeComponent tourneeComponent ;
    private final JourneeMapper journeeMapper ;

    public List<JourneeEntity> getAllJournees(){
        return journeeComponent.findAllJournees();
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
