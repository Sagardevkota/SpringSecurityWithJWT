package com.example.sagar.SpringSecurityWithJWT.model;

public class ReviewResponse {

    private Integer id;
    private String user_name;
    private Integer product_id;
    private String message;

    private String rating;
    private String date;

    public ReviewResponse() {
    }

    public ReviewResponse(Integer id, String user_name, Integer product_id,String message,String rating, String date) {
        this.id = id;
        this.user_name = user_name;
        this.product_id = product_id;
        this.message=message;
        this.rating = rating;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }



    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
