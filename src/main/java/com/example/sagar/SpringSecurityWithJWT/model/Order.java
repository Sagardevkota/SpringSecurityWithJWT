package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "orders")

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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public Float getProductSize() {
        return productSize;
    }

    public void setProductSize(Float productSize) {
        this.productSize = productSize;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getDelivered_date() {
        return delivered_date;
    }

    public void setDelivered_date(String delivered_date) {
        this.delivered_date = delivered_date;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
