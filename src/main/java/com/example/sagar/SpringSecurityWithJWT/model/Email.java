package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @created 17/{07}/2021 - 10:51 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String to;
    private String subject;
    private String text;


}
