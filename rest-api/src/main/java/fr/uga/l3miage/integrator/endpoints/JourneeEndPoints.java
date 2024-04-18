package fr.uga.l3miage.integrator.endpoints;

import fr.uga.l3miage.integrator.response.JourneeResponseDTO;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Journee endpoints")
@RequestMapping("/api/journee")
public interface JourneeEndPoints {

    @Operation(description = "Prend tout les journees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<JourneeResponseDTO> getAllJournees();
}
