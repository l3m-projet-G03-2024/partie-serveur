package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface LivraisonMapper {

    LivraisonResponseDTO toResponse(LivraisonEntity livraisonEntity);

    List<LivraisonResponseDTO> toLivraisonResponse(List<LivraisonEntity> livraisonEntities);
    
    LivraisonEntity toEntity(LivraisonTourneeRequest livraisonTourneeRequest);


}
