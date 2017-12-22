package com.riocode.scoutpro.exception.handler;

import com.riocode.scoutpro.error.AppError;
import com.riocode.scoutpro.exception.PlayerNotFoundException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Nikola Cvetkovic
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<AppError> handlePlayerNotFound(HttpServletRequest req, PlayerNotFoundException ex){
        AppError err = new AppError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, 
                                    ex.getLocalizedMessage(), req.getRequestURI());
        
        return buildResponseEntity(err);
    }
    
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<AppError> handleConstraintViolation(HttpServletRequest req, TransactionSystemException ex){
        Throwable t = null;
        AppError err = null;
        if(ex.getCause() != null && ex.getCause().getCause() != null) {
            t = ex.getCause().getCause();
            if(t instanceof ConstraintViolationException){
                
                err = new AppError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, 
                                    "Validation Error", req.getRequestURI());
                err.extractViolations(((ConstraintViolationException) t).getConstraintViolations());
            }
        } else {
            err = new AppError(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, 
                                ex.getLocalizedMessage(), req.getRequestURI());
        }
        return buildResponseEntity(err);
    }
    
    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        AppError err = new AppError(status.value(), status, "Bind Error", request.getContextPath());
        err.extractBindErrors(ex.getTarget().getClass().getSimpleName(), ex.getFieldErrors());
        
        return buildResponseEntity(err);
    }
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        return new ResponseEntity<>(new AppError(status.value(), status, ex.getLocalizedMessage(), request.getContextPath()), status);
    }
    
    private ResponseEntity buildResponseEntity(AppError err){
        return new ResponseEntity(err, err.getHttpStatus());
    }
    
    
        
}
