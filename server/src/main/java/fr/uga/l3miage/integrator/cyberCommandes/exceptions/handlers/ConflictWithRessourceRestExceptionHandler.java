package fr.uga.l3miage.integrator.cyberCommandes.exceptions.handlers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.CreateJourneeErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.ConflictWithRessourceRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ConflictWithRessourceRestExceptionHandler {
    @ExceptionHandler(ConflictWithRessourceRestException.class)
    public ResponseEntity<CreateJourneeErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){
        ConflictWithRessourceRestException exception = (ConflictWithRessourceRestException) e;
        final CreateJourneeErrorResponse response = CreateJourneeErrorResponse.
                builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(409).body(response);
    }
}
