package fr.uga.l3miage.integrator.cyberRessources.mappers;

import fr.uga.l3miage.integrator.cyberRessources.models.CamionEntity;
import fr.uga.l3miage.integrator.cyberRessources.response.CamionResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CamionMapper {
    List<CamionResponseDTO> toCamionResponseDTO(List<CamionEntity> camionEntities);
}
