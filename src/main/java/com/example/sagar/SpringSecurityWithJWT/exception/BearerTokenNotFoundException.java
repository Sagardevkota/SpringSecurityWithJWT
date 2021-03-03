package com.example.sagar.SpringSecurityWithJWT.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * @created 01/{03}/2021 - 12:56 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BearerTokenNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    public BearerTokenNotFoundException(String message){
        super(message);
    }

}
