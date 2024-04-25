package fr.uga.l3miage.integrator.cyberCommandes.components;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeLivraison;
import fr.uga.l3miage.integrator.cyberCommandes.models.LivraisonEntity;
import fr.uga.l3miage.integrator.cyberVitrine.enums.EtatsDeCommande;
import fr.uga.l3miage.integrator.cyberVitrine.models.CommandeEntity;
import fr.uga.l3miage.integrator.cyberVitrine.repositories.CommandeRepository;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.LivraisonRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LivraisonComponent {
    private final LivraisonRepository livraisonRepository;
    private final CommandeRepository commandeRepository;

    public LivraisonEntity createLivraison(LivraisonEntity livraison, CommandeEntity commande){
        commande.setLivraisonEntity(livraison);
        commandeRepository.save(commande);
        return livraisonRepository.save(livraison);
    }



    public List<LivraisonEntity> findAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public List<LivraisonEntity> findLivraisonByEtat(EtatsDeLivraison etat) {
        return livraisonRepository.findAllByEtat(etat);
    }
}

