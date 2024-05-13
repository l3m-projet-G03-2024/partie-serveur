package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonTourneeRequest;
import fr.uga.l3miage.integrator.cyberCommandes.request.LivraisonUpdateRequest;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonUpdateResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.CommandeMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(uses = CommandeMapper.class)
public interface LivraisonMapper {

    LivraisonResponseDTO toLivraisonResponseDTO(LivraisonEntity livraisonEntity);

    LivraisonUpdateResponseDTO toLivraisonUpdateResponseDTO(LivraisonEntity livraisonEntity);

    List<LivraisonResponseDTO> toLivraisonResponse(List<LivraisonEntity> livraisonEntities);
    
    LivraisonEntity toEntity(LivraisonTourneeRequest livraisonTourneeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLivraisonFromDTO(LivraisonUpdateRequest livraisonUpdateRequest, @MappingTarget LivraisonEntity livraisonEntity);


}
