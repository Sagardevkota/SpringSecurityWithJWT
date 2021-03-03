package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "order_id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "product_color")
    private String productColor;
    @Column(name = "product_size")
    private Float productSize;
    @Column(name = "price")
    private Integer price;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "ordered_date")
    private String orderedDate;
    @Column(name = "delivered_date")
    private String deliveredDate;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "status")
    private String status;






}
