package fr.uga.l3miage.integrator.cyberCommandes.components;

import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.JourneeNotFoundRestException;
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


    public List<JourneeEntity> findAllJournees(){
        return journeeRepository.findAll();
    }
    public JourneeEntity createJourneeEntity(JourneeEntity journeeEntity) {
        return journeeRepository.save(journeeEntity);
    }

    public void deleteJourneeById(String reference) throws JourneeNotFoundException{
        journeeRepository.findById(reference)
                .orElseThrow(()-> new JourneeNotFoundException("Journée non trouvée pour la référence : ", reference));
        journeeRepository.deleteById(reference);

    }
    public JourneeEntity getJourneeById(String reference) throws JourneeNotFoundException {
        return journeeRepository.findById(reference)
                .orElseThrow(() -> new JourneeNotFoundException("Journée non trouvée pour la référence : " ,reference));
    }

    public JourneeEntity updateJournee(JourneeEntity journee) throws EntityNotFoundException{
        return journeeRepository.save(journee);
    }


    public JourneeEntity findJourneeByReference(String refJournee) throws JourneeNotFoundException  {
        return journeeRepository.findById(refJournee)
                .orElseThrow(() -> new JourneeNotFoundException("Journée non trouvée pour la référence : ", refJournee));
    }


}