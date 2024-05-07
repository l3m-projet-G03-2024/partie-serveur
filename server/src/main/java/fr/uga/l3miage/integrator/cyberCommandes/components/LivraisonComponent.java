package fr.uga.l3miage.integrator.cyberCommandes.components;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class LivraisonComponent {
    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;




    public List<LivraisonEntity> findAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public List<LivraisonEntity> findLivraisonByEtat(EtatsDeLivraison etat) {
        return livraisonRepository.findAllByEtat(etat);
    }

    public LivraisonEntity getLivraisonByReference(String reference) throws LivraisonNotFoundException {
        return livraisonRepository.findById(reference)
                .orElseThrow(() ->  new LivraisonNotFoundException("Livraison non trouvée pour la référence : ", reference)) ;
    }
    public List<LivraisonEntity> getLivraisonEntities(Set<LivraisonEntity> livraisonEntities){
        return livraisonRepository.saveAll(livraisonEntities);
   }

    public List<LivraisonEntity> createLivraisons(List<LivraisonEntity> livraisonEntities) {
        return livraisonRepository.saveAll(livraisonEntities);
    }

    public LivraisonEntity updateLivraison(LivraisonEntity livraison){
        return livraisonRepository.save(livraison);
    }

}

