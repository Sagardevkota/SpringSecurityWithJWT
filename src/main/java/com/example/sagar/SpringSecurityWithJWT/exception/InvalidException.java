package com.example.sagar.SpringSecurityWithJWT.exception;
/*
 * @created 01/{03}/2021 - 2:12 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

public class InvalidException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public InvalidException(String message){
        super(message);
    }
}
