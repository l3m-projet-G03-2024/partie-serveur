package fr.uga.l3miage.integrator.cyberCommandes.components;

import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.JourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.JourneeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import fr.uga.l3miage.integrator.cyberCommandes.repositories.JourneeRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class JourneeComponent {
    private final JourneeRepository journeeRepository;


    public Set<JourneeEntity> findAllJournees(){
        return journeeRepository.findAllBy();
    }
    public JourneeEntity createJourneeEntity(JourneeEntity journeeEntity) {
        return journeeRepository.save(journeeEntity);
    }

    public void deleteJourneeById(String reference) throws EntityNotFoundException{
        if(reference != null){
            journeeRepository.deleteById(reference);
        }else {
            throw new EntityNotFoundException("L'entité à supprimer n'a pas été trouvée");
        }

    }
    public JourneeEntity getJourneeById(String reference) throws JourneeNotFoundException {
        return journeeRepository.findById(reference)
                .orElseThrow(() -> new JourneeNotFoundException(String.format(reference, "La journée [%s] n'a pas été trouvé",reference)));
    }

    public JourneeEntity updateJournee(JourneeEntity journee) throws EntityNotFoundException{
        JourneeEntity journeeUpdate = journeeRepository.save(journee);
        if(journeeUpdate != null){
            return journeeUpdate;
        }else{
            throw new EntityNotFoundException("Aucune entité n'a été trouvé pour la description [%s]");
        }
    }
    
}