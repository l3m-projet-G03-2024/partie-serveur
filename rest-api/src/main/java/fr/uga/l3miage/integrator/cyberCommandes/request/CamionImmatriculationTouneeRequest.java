package fr.uga.l3miage.integrator.cyberCommandes.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CamionImmatriculationTouneeRequest {
    @Schema(description = "immatriculation d'un camion")
    private String immatriculation;

    public CamionImmatriculationTouneeRequest(){

    }

    public CamionImmatriculationTouneeRequest(String immatriculation){
        this.immatriculation = immatriculation;
    }

}
