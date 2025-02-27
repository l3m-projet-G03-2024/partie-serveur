package fr.uga.l3miage.integrator.cyberCommandes.components;

import fr.uga.l3miage.integrator.cyberCommandes.enums.EtatsDeTournee;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.technical.TourneeNotFoundException;
import fr.uga.l3miage.integrator.cyberCommandes.models.TourneeEntity;
import fr.uga.l3miage.integrator.cyberCommandes.repositories.TourneeRepository;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.technical.NotFoundEmployeEntityException;
import fr.uga.l3miage.integrator.cyberRessources.models.EmployeEntity;
import fr.uga.l3miage.integrator.cyberRessources.repositories.EmployeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository tourneeRepository;
    private final EmployeRepository employeRepository;

    public List<TourneeEntity> findAllTournee(){
        return tourneeRepository.findAll();
    }

    public List<TourneeEntity> findAllTourneesByEtatOrReferenceJournee(EtatsDeTournee etatsDeTournee,String referenceJournee){
        return tourneeRepository.findByEtatOrJourneeReference(etatsDeTournee,referenceJournee);

    }

    public List<TourneeEntity> createTournees(List<TourneeEntity> tourneeEntities) {
        return tourneeRepository.saveAll(tourneeEntities);
    }


    public TourneeEntity findTourneeByReference(String referenceTournee) throws TourneeNotFoundException  {
        return tourneeRepository.findById(referenceTournee)
                .orElseThrow(() -> new TourneeNotFoundException(String.format("Tournée non trouvée pour la référence : [%s]", referenceTournee)));
    }

    public TourneeEntity addEmployeInTournee(TourneeEntity tourneeEntity){
        return tourneeRepository.save(tourneeEntity);
    }

    public Set<TourneeEntity> getAllTourneesByEmployeEmail(String emailEmploye) throws NotFoundEmployeEntityException {
        EmployeEntity employe = employeRepository.getByEmail(emailEmploye);
        if(employe != null) {
            return new HashSet<>(tourneeRepository.findByEmployesEmail(emailEmploye));
        }else throw new NotFoundEmployeEntityException(String.format("L'email %s n'appartient à aucun employé", emailEmploye));
    }

    public TourneeEntity saveTournee(TourneeEntity tourneeEntity) {
        return tourneeRepository.save(tourneeEntity);
    }
}