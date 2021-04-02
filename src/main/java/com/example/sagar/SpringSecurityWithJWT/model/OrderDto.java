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
    private String color;
    private Float size;
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




}
