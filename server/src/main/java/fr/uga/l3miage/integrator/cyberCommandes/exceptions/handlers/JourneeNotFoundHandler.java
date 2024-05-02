package fr.uga.l3miage.integrator.cyberCommandes.exceptions.handlers;


import fr.uga.l3miage.integrator.cyberCommandes.errors.JourneeNotFoundResponse;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.JourneeNotFoundRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class JourneeNotFoundHandler {

    @ExceptionHandler(JourneeNotFoundRestException.class)
    public ResponseEntity<JourneeNotFoundResponse> handle(HttpServletRequest httpServletRequest, Exception exception) {
        JourneeNotFoundRestException journeeNotFoundRestException = (JourneeNotFoundRestException) exception;
        JourneeNotFoundResponse response = JourneeNotFoundResponse
                .builder()
                .refJournee(journeeNotFoundRestException.getReference())
                .errorMessage(journeeNotFoundRestException.getMessage()+""+journeeNotFoundRestException.getReference())
                .uri(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(404).body(response);
    }
}
