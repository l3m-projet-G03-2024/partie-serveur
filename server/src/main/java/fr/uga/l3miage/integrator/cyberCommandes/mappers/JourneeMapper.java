package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;

import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface JourneeMapper {
    List<JourneeDetailResponseDTO> toJourneeDetailResponseDTOS(List<JourneeEntity> journeeEntities);
    public JourneeDetailResponseDTO toJourneeDetailResponseDTO(JourneeEntity journeeEntity);

    JourneeEntity toEntity(JourneeCreationRequest journeeRequest) ;

    JourneeResponseDTO toJourneeResponseDTO(JourneeEntity journeeEntity) ;


}
