package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {


    private Integer orderId;
    private Integer productId;
    private String userName;
    private String phone;
    private String productColor;
    private Float productSize;
    private Integer price;
    private Integer quantity;
    private String orderedDate;
    private String deliveredDate;
    private String deliveryAddress;
    private String status;

    //productsInfo
    private String productName;
    private Integer discount;
    private String picturePath;


//
//    //for user
//    public OrderDto(Integer order_id, Integer product_id, String product_color, Float product_size, Integer price, Integer quantity, String ordered_date, String delivered_date, String delivery_address, String status, String product_name, Integer discount, String picture_path) {
//        this.orderId = order_id;
//        this.productId = product_id;
//        this.productColor = product_color;
//        this.productSize = product_size;
//        this.price = price;
//        this.quantity = quantity;
//        this.orderedDate = ordered_date;
//        this.deliveredDate = delivered_date;
//        this.deliveryAddress = delivery_address;
//        this.status = status;
//        this.productName = product_name;
//        this.discount = discount;
//        this.picturePath = picture_path;
//    }
//
//    //for seller
//
//    public OrderDto(Integer order_id, String user_name, String phone, Integer product_id, String product_color, Float product_size, Integer price, Integer quantity, String ordered_date, String delivered_date, String delivery_address, String status, String product_name, Integer discount, String picture_path) {
//        this.orderId = order_id;
//        this.userName=user_name;
//        this.phone=phone;
//        this.productId = product_id;
//        this.productColor = product_color;
//        this.productSize = product_size;
//        this.price = price;
//        this.quantity = quantity;
//        this.orderedDate = ordered_date;
//        this.deliveredDate = delivered_date;
//        this.deliveryAddress = delivery_address;
//        this.status = status;
//        this.productName = product_name;
//        this.discount = discount;
//        this.picturePath = picture_path;
//    }


}
