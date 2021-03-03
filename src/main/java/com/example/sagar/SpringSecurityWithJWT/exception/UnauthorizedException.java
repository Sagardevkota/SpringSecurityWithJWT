package com.example.sagar.SpringSecurityWithJWT.exception;
/*
 * @created 01/{03}/2021 - 2:29 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

public class UnauthorizedException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UnauthorizedException(String message){
        super(message);
    }
}
