package fr.uga.l3miage.integrator.cyberVitrine.mappers;

import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CommandeMapper {

    List<CommandeResponseDTO> toCommandeResponseDTO(List<CommandeEntity> commandeEntities);
}
