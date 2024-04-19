package fr.uga.l3miage.integrator.cyberCommandes.components;

import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository;

    public List<JourneeEntity> findAllJournees(){
        return journeeRepository.findAll();
    }

<<<<<<< HEAD
    public JourneeEntity createJourneeEntity(JourneeEntity journeeEntity){
        return journeeRepository.save(journeeEntity) ;
=======
    public void deleteJourneeById(String reference){
        journeeRepository.deleteById(reference);
>>>>>>> 19a9ad5 (feat: implementation de delete par reference)
    }
}