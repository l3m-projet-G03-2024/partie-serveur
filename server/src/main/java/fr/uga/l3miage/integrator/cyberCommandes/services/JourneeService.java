package fr.uga.l3miage.integrator.cyberCommandes.services;

import fr.uga.l3miage.integrator.cyberCommandes.components.JourneeComponent;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeJournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.*;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberProduit.exceptions.technical.EntrepotNotFoundException;
import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.components.EntrepotComponent;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JourneeService {
    private final JourneeComponent journeeComponent;
    private final JourneeMapper journeeMapper;
    private final EntrepotComponent entrepotComponent;

    public List<JourneeDetailResponseDTO> findAllJournees(){
        try {
            List<JourneeEntity> journeeEntities = journeeComponent.findAllJournees();
            return journeeMapper.toJourneeDetailResponseDTOS(journeeEntities);
        }catch (Exception e){
            throw new NotFoundRestException(e.getMessage());
        }

    }

    public void deleteJourneeById(String reference){
        try {
            journeeComponent.deleteJourneeById(reference);
        }catch (JourneeNotFoundException | NotFoundRestException e) {
            throw new NotFoundRestException(e.getMessage());
        }catch (BadRequestRestException e){
            throw new BadRequestRestException(e.getMessage());
        }catch (ForbiddenRestException e){
            throw new ForbiddenRestException(e.getMessage());
        } catch (DeleteFailedRestException e){
            throw new DeleteFailedRestException(e.getMessage());
        }

    }

    public JourneeDetailResponseDTO createJournee(JourneeCreationRequest journeeRequest){
        try{
            JourneeEntity journeeEntity = journeeMapper.toEntity(journeeRequest) ;
            EntrepotEntity entrepotEntity = entrepotComponent.getEntrepotByNom(journeeRequest.getNomEntrepot());
            journeeEntity.setEtat(EtatsDeJournee.NONPLANIFIEE) ;
            journeeEntity.setEntrepotEntity(entrepotEntity);
            return journeeMapper.toJourneeDetailResponseDTO(journeeComponent.createJourneeEntity(journeeEntity)) ;
        }catch (CreationFailedRestException e) {
            throw new CreationFailedRestException(e.getMessage());
        } catch (EntrepotNotFoundException e) {
            throw new NotFoundRestException(e.getMessage());
        }catch (BadRequestRestException e){
            throw new BadRequestRestException(e.getMessage());
        }catch (ForbiddenRestException e){
            throw new ForbiddenRestException(e.getMessage());
        }
    }
    public JourneeDetailResponseDTO getJournee(String reference) {
        try {
            JourneeEntity journeeEntity = journeeComponent.getJourneeById(reference);
            return journeeMapper.toJourneeDetailResponseDTO(journeeEntity);
        }catch (JourneeNotFoundException | NotFoundRestException e){
            throw new NotFoundRestException(e.getMessage());
        }
    }

    public JourneeDetailResponseDTO updateJournee(String reference, JourneeUpdateRequest journeeUpdate){

        try {
            JourneeEntity journeeExist = journeeComponent.getJourneeById(reference);
            journeeMapper.updateJourneeFromDTO(journeeUpdate, journeeExist);
            return journeeMapper.toJourneeDetailResponseDTO(journeeComponent.updateJournee(journeeExist));
        }catch (JourneeNotFoundException | NotFoundRestException e) {
            throw new NotFoundRestException(e.getMessage());
        }catch (BadRequestRestException e){
            throw new BadRequestRestException(e.getMessage());
        }catch (ForbiddenRestException e){
            throw new ForbiddenRestException(e.getMessage());
        } catch (UpdateFailedRestException e){
            throw new UpdateFailedRestException(e.getMessage());
        }
    }
}
