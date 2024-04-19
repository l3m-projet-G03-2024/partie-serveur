package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeDetailResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface JourneeMapper {
    List<JourneeResponseDTO> toJourneeResponseDTOS(List<JourneeEntity> journeeEntities);

    JourneeEntity toEntity(JourneeRequest journeeRequest) ;

    JourneeResponseDTO toJourneeResponseDTO(JourneeEntity journeeEntity) ;
    public JourneeDetailResponseDTO toJourneeDeatilResponseDTO(JourneeEntity journeeEntity);
}
