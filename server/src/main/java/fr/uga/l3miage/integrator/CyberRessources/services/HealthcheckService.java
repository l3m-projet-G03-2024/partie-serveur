package fr.uga.l3miage.integrator.CyberRessources.services;

import fr.uga.l3miage.integrator.CyberRessources.components.HealthcheckComponent;
import fr.uga.l3miage.integrator.CyberRessources.exceptions.rest.HealthcheckRestException;
import fr.uga.l3miage.integrator.CyberRessources.exceptions.technical.DataBaseHealthCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HealthcheckService {
    private final HealthcheckComponent healthcheckComponent;

    public void checkServerHealth(){
        try {
            healthcheckComponent.checkHealthDataBase();
        } catch (DataBaseHealthCheckException e) {
            throw new HealthcheckRestException(e.getMessage(), HealthcheckRestException.Type.DATABASE);
        }
    }

}
