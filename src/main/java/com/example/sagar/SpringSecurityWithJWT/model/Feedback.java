package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;

@Entity(name = "feedback")
@Table(name = "feedback")
public class Feedback {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "message")
    private String message;
    @Column(name="subject")
    private String subject;



    public Feedback(Integer user_id,String subject, String message) {
        this.user_id = user_id;
        this.subject=subject;
        this.message = message;
    }

    public Feedback() {
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
