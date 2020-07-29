package com.example.sagar.SpringSecurityWithJWT.controller;



public class OrderResponse {


    private Integer order_id;

    private Integer product_id;

    private String user_name;

    private String phone;


    private String product_color;

    private Float product_size;

    private Integer price;

    private Integer quantity;

    private String ordered_date;

    private String delivered_date;

    private String delivery_address;

    private String status;

    //productsInfo
    private String product_name;
    private Integer discount;
    private String picture_path;



    //for user
    public OrderResponse(Integer order_id, Integer product_id, String product_color, Float product_size, Integer price, Integer quantity, String ordered_date, String delivered_date, String delivery_address, String status, String product_name, Integer discount, String picture_path) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_color = product_color;
        this.product_size = product_size;
        this.price = price;
        this.quantity = quantity;
        this.ordered_date = ordered_date;
        this.delivered_date = delivered_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.product_name = product_name;
        this.discount = discount;
        this.picture_path = picture_path;
    }

    //for seller

    public OrderResponse(Integer order_id, String user_name, String phone, Integer product_id, String product_color, Float product_size, Integer price, Integer quantity, String ordered_date, String delivered_date, String delivery_address, String status, String product_name, Integer discount, String picture_path) {
        this.order_id = order_id;
        this.user_name=user_name;
        this.phone=phone;
        this.product_id = product_id;
        this.product_color = product_color;
        this.product_size = product_size;
        this.price = price;
        this.quantity = quantity;
        this.ordered_date = ordered_date;
        this.delivered_date = delivered_date;
        this.delivery_address = delivery_address;
        this.status = status;
        this.product_name = product_name;
        this.discount = discount;
        this.picture_path = picture_path;
    }

    public OrderResponse(OrderResponse orderResponse) {
        this.order_id = orderResponse.getOrder_id();
        this.product_id = orderResponse.getProduct_id();
        this.product_color = orderResponse.getProduct_color();
        this.product_size = orderResponse.getProduct_size();
        this.price = orderResponse.getPrice();
        this.quantity = orderResponse.getQuantity();
        this.ordered_date = orderResponse.getOrdered_date();
        this.delivered_date = orderResponse.getOrdered_date();
        this.delivery_address = orderResponse.getDelivery_address();
        this.status = orderResponse.getStatus();
        this.product_name = orderResponse.getProduct_name();
        this.discount = orderResponse.getDiscount();
        this.picture_path = orderResponse.getPicture_path();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_color() {
        return product_color;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public Float getProduct_size() {
        return product_size;
    }

    public void setProduct_size(Float product_size) {
        this.product_size = product_size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrdered_date() {
        return ordered_date;
    }

    public void setOrdered_date(String ordered_date) {
        this.ordered_date = ordered_date;
    }

    public String getDelivered_date() {
        return delivered_date;
    }

    public void setDelivered_date(String delivered_date) {
        this.delivered_date = delivered_date;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }
}
