package fr.uga.l3miage.integrator.cyberCommandes.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CamionImmatriculationTouneeRequest {
    private String immatriculation;

    public CamionImmatriculationTouneeRequest(){

    }

    public CamionImmatriculationTouneeRequest(String immatriculation){
        this.immatriculation = immatriculation;
    }

}
