package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "conversations")
@Table(name = "conversations")
public class Conversation {

    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private String productId;
    private String message;
    @Column(name = "user_id")
    private Integer asker;
    @Column(name = "date")
    private String date;

    public Conversation() {
    }

    public Conversation(String productId, String message, Integer asker, String date) {
        this.productId = productId;
        this.message = message;
        this.asker = asker;
        this.date = date;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAsker() {
        return asker;
    }

    public void setAsker(Integer asker) {
        this.asker = asker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
