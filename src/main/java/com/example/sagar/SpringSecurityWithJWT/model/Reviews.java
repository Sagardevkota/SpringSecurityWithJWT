package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;

@Table(name = "reviews")
@Entity(name = "reviews")
public class Reviews {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private String rating;

    @Column(name = "date")
    private String date;

    public Reviews() {
    }

    public Reviews(Integer id, Integer user_id, Integer product_id, String message, String rating, String date) {
        this.id = id;
        this.user_id = user_id;
        this.product_id = product_id;
        this.message = message;
        this.rating = rating;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
