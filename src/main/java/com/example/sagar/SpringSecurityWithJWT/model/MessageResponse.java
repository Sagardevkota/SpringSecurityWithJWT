package com.example.sagar.SpringSecurityWithJWT.model;

public class MessageResponse {

    private Integer product_id;
    private String picture_path;
    private String product_name;
    private String message;
    private String user_name;
    private String date;



    public MessageResponse(Integer product_id, String picture_path, String product_name, String message, String user_name,String date) {
        this.product_id = product_id;
        this.picture_path = picture_path;
        this.product_name = product_name;
        this.message = message;
        this.user_name = user_name;
        this.date=date;
    }

    public MessageResponse(MessageResponse messageResponse) {
        this.product_id=messageResponse.getProduct_id();
        this.picture_path=messageResponse.getPicture_path();
        this.product_name=messageResponse.getProduct_name();
        this.message=messageResponse.getMessage();
        this.user_name=messageResponse.getUser_name();
        this.date=messageResponse.getDate();
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
