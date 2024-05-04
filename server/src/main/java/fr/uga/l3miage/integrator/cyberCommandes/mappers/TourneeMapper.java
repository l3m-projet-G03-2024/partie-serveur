package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;;;


@Mapper(uses = CommandeMapper.class)
public interface TourneeMapper {


    TourneeResponseDTO toTourneeResponseDTO(TourneeEntity tourneeEntity);


    List<TourneeResponseDTO> toTourneesResponseDTO(List<TourneeEntity> tourneeEntities);

    TourneeEntity toEntity(TourneeCreationRequest tourneeCreationRequest);
    

}
