package fr.uga.l3miage.integrator.cyberVitrine.mappers;

import fr.uga.l3miage.integrator.cyberProduit.mappers.ProduitMapper;
import fr.uga.l3miage.integrator.cyberVitrine.requests.CommandeUpdatingRequest;
import fr.uga.l3miage.integrator.cyberVitrine.response.CommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.response.DetailsCommandeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;

import java.util.List;
@Mapper(uses = {LigneMapper.class, ProduitMapper.class})
public interface CommandeMapper {

    List<CommandeResponseDTO> toCommandesResponseDTO(List<CommandeEntity> commandeEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCommandeFromDTO(CommandeUpdatingRequest commandeUpdatingRequest, @MappingTarget CommandeEntity commandeEntity) ;

    @Mapping(source = "clientEntity", target = "client")
    @Mapping(source = "dateDeCreation", target = "dateDeCreation")
    CommandeResponseDTO toCommandeResponseDTO(CommandeEntity commandeEntity) ;

    @Mapping(source = "ligneEntities",target = "lignes")
    DetailsCommandeResponseDTO toDetailsCommandeResponseDTO(CommandeEntity commande);
}
