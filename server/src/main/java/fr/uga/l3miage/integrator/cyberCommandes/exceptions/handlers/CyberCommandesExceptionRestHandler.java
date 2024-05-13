package fr.uga.l3miage.integrator.cyberCommandes.exceptions.handlers;

import fr.uga.l3miage.integrator.cyberCommandes.errors.*;
import fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CyberCommandesExceptionRestHandler {

    @ExceptionHandler(BadRequestRestException.class)
    public ResponseEntity<BadRequestErrorResponse> handleBadRequest(HttpServletRequest httpServletRequest, Exception e){
        BadRequestRestException exception = (BadRequestRestException) e;
        final BadRequestErrorResponse response = BadRequestErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(400).body(response);
    }
    @ExceptionHandler(ForbiddenRestException.class)
    public ResponseEntity<ForbiddenErrorResponse> handleForbiddenRequest(HttpServletRequest httpServletRequest, Exception e){
        ForbiddenRestException exception = (ForbiddenRestException) e;
        final ForbiddenErrorResponse response = ForbiddenErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(403).body(response);
    }

    @ExceptionHandler(NotFoundRestException.class)
    public ResponseEntity<NotFoundErrorResponse> handleNotFoundEntity(HttpServletRequest httpServletRequest, Exception  e){
        NotFoundRestException exception = (NotFoundRestException ) e;
        final NotFoundErrorResponse response = NotFoundErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
                log.warn(exception.getMessage());
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(UpdateFailedRestException.class)
    public ResponseEntity<UpdateFailedErrorResponse> handleUpdateFailed(HttpServletRequest httpServletRequest, Exception e){
        BadRequestRestException exception = (BadRequestRestException) e;
        final UpdateFailedErrorResponse response = UpdateFailedErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(DeleteFailedRestException.class)
    public ResponseEntity<DeleteFailedErrorResponse> handleDeleteFailed(HttpServletRequest httpServletRequest, Exception e){
        DeleteFailedRestException exception = (DeleteFailedRestException) e;
        final DeleteFailedErrorResponse response = DeleteFailedErrorResponse
                .builder()
                .errorMessage(exception.getMessage()+exception.getReference())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(CreationFailedRestException.class)
    public ResponseEntity<CreationFailedErrorResponse> handleCreationFailed(HttpServletRequest httpServletRequest, Exception e){
        CreationFailedRestException exception = (CreationFailedRestException) e;
        final CreationFailedErrorResponse response = CreationFailedErrorResponse
                .builder()
                .errorMessage(exception.getMessage())
                .uri(httpServletRequest.getRequestURI())
                .build();
        log.warn(exception.getMessage());
        return ResponseEntity.status(500).body(response);
    }
}


