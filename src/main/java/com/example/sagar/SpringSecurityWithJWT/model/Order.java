package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "orders")
@Getter
@Setter
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
    private Integer qty;
    @Column(name = "ordered_date")
    private String orderedDate;
    @Column(name = "delivered_date")
    private String delivered_date;
    @Column(name = "delivery_address")
    private String deliveryAddress;
    @Column(name = "status")
    private String status;

    public Order() {

    }

    public Order(Integer orderId, Integer productId, Integer userId, String productColor, Float productSize, Integer price, Integer qty, String orderedDate, String deliveryAddress) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.productColor = productColor;
        this.productSize = productSize;
        this.price = price;
        this.qty = qty;
        this.orderedDate = orderedDate;
        this.deliveryAddress = deliveryAddress;
    }

    public Order(Integer productId, Integer userId, String productColor, Float productSize, Integer price, Integer qty, String orderedDate, String delivered_date, String deliveryAddress, String status) {
        this.productId = productId;
        this.userId = userId;
        this.productColor = productColor;
        this.productSize = productSize;
        this.price = price;
        this.qty = qty;
        this.orderedDate = orderedDate;
        this.delivered_date = delivered_date;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
    }


}
