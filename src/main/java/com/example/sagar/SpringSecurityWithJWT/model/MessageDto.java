package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MessageDto {

    private Integer product_id;
    private String picture_path;
    private String product_name;
    private String message;
    private String user_name;
    private String date;


    public MessageDto(MessageDto messageDto) {
        this.product_id= messageDto.getProduct_id();
        this.picture_path= messageDto.getPicture_path();
        this.product_name= messageDto.getProduct_name();
        this.message= messageDto.getMessage();
        this.user_name= messageDto.getUser_name();
        this.date= messageDto.getDate();
    }


}
