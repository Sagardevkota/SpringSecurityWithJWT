package com.example.sagar.SpringSecurityWithJWT.model;

public class JwtResponse {
    private  String jwt;
    private String status;
    private String message;
    private String role;

    public JwtResponse(String jwt, String status, String message,String role) {
        this.jwt = jwt;
        this.status = status;
        this.message = message;
        this.role=role;
    }

    public JwtResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getJwt() {
        return jwt;
    }
}
