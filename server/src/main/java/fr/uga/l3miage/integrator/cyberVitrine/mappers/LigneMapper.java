package fr.uga.l3miage.integrator.cyberVitrine.mappers;

import fr.uga.l3miage.integrator.cyberProduit.mappers.ProduitMapper;
import fr.uga.l3miage.integrator.cyberVitrine.models.LigneEntity;
import fr.uga.l3miage.integrator.cyberVitrine.response.LigneCommandeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = ProduitMapper.class)
public interface LigneMapper {

    @Mapping(source = "produitEntity",target = "produit")
    LigneCommandeResponseDTO toLigneCommandeResponseDTO(LigneEntity ligneEntity);

}
