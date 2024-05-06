package fr.uga.l3miage.integrator.cyberProduit.mappers;

import fr.uga.l3miage.integrator.cyberProduit.models.EntrepotEntity;
import fr.uga.l3miage.integrator.cyberProduit.response.EntrepotResponseDetailDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface EntrepotMapper {
    List<EntrepotResponseDetailDTO> toEntrepotResponseDetailDTOS(List<EntrepotEntity> entities);
}
