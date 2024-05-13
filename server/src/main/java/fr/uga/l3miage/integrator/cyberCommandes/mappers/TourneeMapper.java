package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import java.util.List;

import fr.uga.l3miage.integrator.cyberCommandes.request.TourneeCreationRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.AddCamionOnTourneeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import fr.uga.l3miage.integrator.cyberCommandes.mappers.JourneeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;;;


@Mapper(uses = {CommandeMapper.class, JourneeMapper.class})
public interface TourneeMapper {


    TourneeResponseDTO toTourneeResponseDTO(TourneeEntity tourneeEntity);


    List<TourneeResponseDTO> toTourneesResponseDTO(List<TourneeEntity> tourneeEntities);

    TourneeEntity toEntity(TourneeCreationRequest tourneeCreationRequest);

    TourneeEntity toEntity(TourneeResponseDTO tourneeResponseDTO);

    @Mapping(source = "camion", target = "camion")
    AddCamionOnTourneeResponseDTO toTourneeCamionResponseDTO(TourneeEntity tourneeEntity);


}
