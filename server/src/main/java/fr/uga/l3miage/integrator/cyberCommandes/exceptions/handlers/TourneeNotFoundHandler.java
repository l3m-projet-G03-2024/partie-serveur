package fr.uga.l3miage.integrator.cyberCommandes.exceptions.handlers;


import fr.uga.l3miage.integrator.cyberCommandes.errors.TourneeNotFoundResponse;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.TourneeNotFoundRestException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class TourneeNotFoundHandler {

    @ExceptionHandler(TourneeNotFoundRestException.class)
    public ResponseEntity<TourneeNotFoundResponse> handle(HttpServletRequest httpServletRequest, Exception exception) {
        TourneeNotFoundRestException tourneeNotFoundRestException = (TourneeNotFoundRestException) exception;
        TourneeNotFoundResponse response = TourneeNotFoundResponse
                .builder()
                .referenceTournee(tourneeNotFoundRestException.getReference())
                .errorMessage(tourneeNotFoundRestException.getMessage()+""+tourneeNotFoundRestException.getReference())
                .uri(httpServletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(404).body(response);
    }

}
