package fr.uga.l3miage.integrator.cyberCommandes.components;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.LivraisonEntityNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
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



    public List<LivraisonEntity> findAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public List<LivraisonEntity> findLivraisonByEtat(EtatsDeLivraison etat) {
        return livraisonRepository.findAllByEtat(etat);
    }

    public LivraisonEntity getLivraisonByReference(String reference) throws LivraisonEntityNotFoundException {
        return livraisonRepository.findById(reference)
                .orElseThrow(() ->  new LivraisonEntityNotFoundException(String.format(reference, "La livraison [%s] n'a pas été trouvée", reference))) ;
    }
    public List<LivraisonEntity> getLivraisonEntities(Set<LivraisonEntity> livraisonEntities){
         return livraisonRepository.saveAll(livraisonEntities);
    }

    public List<LivraisonEntity> createLivraisons(List<LivraisonEntity> livraisonEntities) {
        return livraisonRepository.saveAll(livraisonEntities);
    }


}

