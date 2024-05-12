package fr.uga.l3miage.integrator.cyberProduit.mappers;

import fr.uga.l3miage.integrator.cyberProduit.models.ProduitEntity;
import fr.uga.l3miage.integrator.cyberProduit.response.ProduitLigneCommandeResponseDTO;
import fr.uga.l3miage.integrator.cyberVitrine.mappers.LigneMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProduitMapper {

    ProduitLigneCommandeResponseDTO toProduitLigneCommandeResponseDTO(ProduitEntity produitEntity) ;

}
