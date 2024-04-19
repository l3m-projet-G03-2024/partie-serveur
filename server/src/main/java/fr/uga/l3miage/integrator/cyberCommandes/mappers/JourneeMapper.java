package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.JourneeResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface JourneeMapper {
    List<JourneeResponseDTO> toJourneeResponseDTOS(List<JourneeEntity> journeeEntities);
}
