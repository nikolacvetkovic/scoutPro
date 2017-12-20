package com.riocode.scoutpro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Nikola Cvetkovic
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends RuntimeException{
    
    public PlayerNotFoundException(){
        super();
    }
    
    public PlayerNotFoundException(String message){
        super(message);
    }
    
    public PlayerNotFoundException(Throwable t){
        super(t);
    }
    
    public PlayerNotFoundException(String message, Throwable t){
        super(message, t);
    }
}
