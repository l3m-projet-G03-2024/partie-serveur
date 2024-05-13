package fr.uga.l3miage.integrator.cyberRessources.exceptions.handlers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.EmployeNotFoundErrorResponse;
import fr.uga.l3miage.integrator.cyberRessources.exceptions.rest.EmployeNotFoundRestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EmployeNotFoundEntityHandler {
    @ExceptionHandler(EmployeNotFoundRestException.class)
    public ResponseEntity<EmployeNotFoundErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception){
        EmployeNotFoundRestException employeNotFoundRestException = (EmployeNotFoundRestException) exception;
        EmployeNotFoundErrorResponse response = EmployeNotFoundErrorResponse
                .builder()
                .emailEmploye(employeNotFoundRestException.getEmailEmploye())
                .uri(httpServletRequest.getRequestURI())
                .errorMessage(employeNotFoundRestException.getMessage())
                .build();
        return ResponseEntity.status(404).body(response);
    }


}
