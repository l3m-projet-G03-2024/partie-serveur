package fr.uga.l3miage.integrator.cyberVitrine.mappers;

import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface CommandeMapper {

    List<CommandeResponseDTO> toCommandesResponseDTO(List<CommandeEntity> commandeEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommandeFromDTO(CommandeUpdatingRequest commandeUpdatingRequest, @MappingTarget CommandeEntity commandeEntity) ;

    @Mapping(source = "clientEntity", target = "client")
    @Mapping(source = "dateDeCreation", target = "dateDeCreation")
    CommandeResponseDTO toCommandeResponseDTO(CommandeEntity commandeEntity) ;
}
