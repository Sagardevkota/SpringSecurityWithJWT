package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private Integer id;
    private String user_name;
    private Integer product_id;
    private String message;
    private String rating;
    private String date;


}
