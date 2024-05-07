package fr.uga.l3miage.integrator.cyberRessources.exceptions.handlers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.EmployeNotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.EmployeRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EmployeNotFoundEntityHandler {
    @ExceptionHandler(EmployeRestException.class)
    public ResponseEntity<EmployeNotFoundErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception){
        EmployeRestException employeRestException = (EmployeRestException) exception;
        EmployeNotFoundErrorResponse response = EmployeNotFoundErrorResponse
                .builder()
                .emailEmploye(employeRestException.getEmailEmploye())
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(employeRestException.getMessage())
                .build();
        return ResponseEntity.status(404).body(response);
    }
}
