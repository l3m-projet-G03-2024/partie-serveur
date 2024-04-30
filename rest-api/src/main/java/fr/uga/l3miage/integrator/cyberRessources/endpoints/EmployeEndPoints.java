package fr.uga.l3miage.integrator.cyberRessources.endpoints;

import fr.uga.l3miage.integrator.cyberRessources.enums.Emploi;
import fr.uga.l3miage.integrator.cyberRessources.response.EmployeResponseDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Employe endpoints")
@RequestMapping("/api/v1/employes")
@CrossOrigin("*")
public interface EmployeEndPoints {

    @ApiResponse(responseCode = "200",description = "La liste des employes")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    Set<EmployeResponseDTO> listEmployeResponseDtoSet(@RequestParam(required = false) Emploi emploi);

}
