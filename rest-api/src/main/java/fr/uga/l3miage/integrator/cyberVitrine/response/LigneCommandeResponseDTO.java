package fr.uga.l3miage.integrator.cyberVitrine.response;

import fr.uga.l3miage.integrator.cyberProduit.response.ProduitLigneCommandeResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeResponseDTO {

    private String id;

    private ProduitLigneCommandeResponseDTO produit;
}
