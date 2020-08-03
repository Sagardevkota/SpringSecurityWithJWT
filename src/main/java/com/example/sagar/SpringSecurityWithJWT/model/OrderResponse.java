package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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


}
