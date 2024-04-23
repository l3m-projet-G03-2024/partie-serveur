package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.TourneeResponseDTO;;;


@Mapper
public interface TourneeMapper {
    @Mapping(target = "livraisonEntities", source = "livraisons") 
    @Mapping(target = "etatsDeTournee", source = "etat") 
    TourneeResponseDTO toTourneeResponseDTO(TourneeEntity tourneeEntity);
    List<TourneeResponseDTO> toTourneeResponseDTO(List<TourneeEntity> tourneeEntities);
    

}
