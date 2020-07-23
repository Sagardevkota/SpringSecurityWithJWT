package com.example.sagar.SpringSecurityWithJWT.model;


public class ConversationResponse {
    private String message;
    private String user_name;
    private String date;

    public ConversationResponse(String message, String user_name, String date) {
        this.message = message;
        this.user_name = user_name;
        this.date = date;
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
