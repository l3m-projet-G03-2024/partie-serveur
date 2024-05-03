package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.JourneeUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface LivraisonMapper {

    LivraisonResponseDTO toResponse(LivraisonEntity livraisonEntity);

    List<LivraisonResponseDTO> toLivraisonResponse(List<LivraisonEntity> livraisonEntities);
    
    LivraisonEntity toEntity(LivraisonTourneeRequest livraisonTourneeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLivraisonFromDTO(LivraisonUpdateRequest livraisonUpdateRequest, @MappingTarget LivraisonEntity livraisonEntity);


}
