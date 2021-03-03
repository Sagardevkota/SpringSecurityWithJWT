package com.example.sagar.SpringSecurityWithJWT.model;
/*
 * @created 03/{03}/2021 - 9:57 AM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {
    @NotEmpty(message = "Username field can not be empty or missed")
    @Size(min = 5, max = 128, message = "Username field must have from 5 to 128 symbols")
    @Email
    private String userName;

    @NotEmpty(message = "Password field cant be empty")
    private String password;
}
