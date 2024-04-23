package fr.uga.l3miage.integrator.cyberCommandes.components;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;


@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository tourneeRepository;

    public List<TourneeEntity> findAllTournee(){
        return tourneeRepository.findAll();
    }

    public List<TourneeEntity> findAllTourneesByEtat(EtatsDeTournee etatsDeTournee){
        return tourneeRepository.findAllByEtat(etatsDeTournee);

    }

    
}