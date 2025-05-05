package pelis.cloud.peliscloudjpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PeliculasNotFoundException extends RuntimeException {
    
    public PeliculasNotFoundException(String message) {
        super(message);
    }


}