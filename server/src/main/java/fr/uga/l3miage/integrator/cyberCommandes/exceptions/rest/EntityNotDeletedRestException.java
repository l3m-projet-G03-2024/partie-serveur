package fr.uga.l3miage.integrator.cyberCommandes.exceptions.rest;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class EntityNotDeletedRestException extends RuntimeException{

    public EntityNotDeletedRestException(String message){
        super(message);
    }

    public EntityNotDeletedRestException(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus getHttpStatus(){
        return HttpStatus.I_AM_A_TEAPOT;
    }

}
