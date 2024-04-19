package fr.uga.l3miage.integrator.cyberCommandes.mappers;

import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberCommandes.response.LivraisonResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface LivraisonMapper {

    LivraisonResponseDTO toResponse(LivraisonEntity livraisonEntity);

}
