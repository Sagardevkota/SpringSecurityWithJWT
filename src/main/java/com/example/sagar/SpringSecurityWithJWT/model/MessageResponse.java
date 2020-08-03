package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageResponse {

    private Integer product_id;
    private String picture_path;
    private String product_name;
    private String message;
    private String user_name;
    private String date;


    public MessageResponse(MessageResponse messageResponse) {
        this.product_id=messageResponse.getProduct_id();
        this.picture_path=messageResponse.getPicture_path();
        this.product_name=messageResponse.getProduct_name();
        this.message=messageResponse.getMessage();
        this.user_name=messageResponse.getUser_name();
        this.date=messageResponse.getDate();
    }


}
