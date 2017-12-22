package com.riocode.scoutpro.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Nikola Cvetkovic
 */

public class AppError {
    
    private int httpStatusCode;
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<SubAppErr> subErrors;
    private String path;

    public AppError(int httpStatusCode, HttpStatus httpStatus, String message, String path){
        this.timestamp = LocalDateTime.now();
        this.httpStatusCode = httpStatusCode;
        this.httpStatus = httpStatus;
        this.message = message;
        this.path = path;
    }
    
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
    
    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
    
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<SubAppErr> getSubErrors() {
        return subErrors;
    }
    
    public void extractViolations(Set<ConstraintViolation<?>> set){
        if(this.subErrors == null) this.subErrors = new ArrayList<>();
        for (ConstraintViolation c : set) {
            ConstraintViolationSubAppErr subAppErr 
                    = new ConstraintViolationSubAppErr(c.getRootBeanClass().getSimpleName(),((PathImpl)c.getPropertyPath()).getLeafNode().asString(), c.getInvalidValue().toString(), c.getMessage());
            this.subErrors.add(subAppErr);
        }
    }
}
