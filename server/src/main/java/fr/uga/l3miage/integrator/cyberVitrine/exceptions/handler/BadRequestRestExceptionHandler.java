package fr.uga.l3miage.integrator.cyberVitrine.exceptions.handler;

import fr.uga.l3miage.integrator.cyberVitrine.exceptions.BadRequestErrorResponse;
import fr.uga.l3miage.integrator.cyberVitrine.exceptions.rest.BadRequestRestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class BadRequestRestExceptionHandler {
    @ExceptionHandler(BadRequestRestException.class)
    public ResponseEntity<BadRequestErrorResponse> handle(HttpServletRequest httpServletRequest, Exception e){
        BadRequestRestException exception = (BadRequestRestException) e;
        final BadRequestErrorResponse response = BadRequestErrorResponse.
                builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(400).body(response);
    }
}
