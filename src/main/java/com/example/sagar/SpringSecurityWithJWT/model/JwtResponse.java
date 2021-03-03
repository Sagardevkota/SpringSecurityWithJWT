package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JwtResponse {
    private  String jwt;
    private String status;
    private String message;
    private String role;


    public JwtResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
