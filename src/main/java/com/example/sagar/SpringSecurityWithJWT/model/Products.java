package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;

@Entity(name = "products")
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "product_id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String desc;

    @Column(name = "price")
    private String price;
    @Column(name = "category")
    private String category;
    @Column(name = "brand")
    private String brand;
    @Column(name = "sku")
    private String sku;
    @Column(name = "type")
    private String type;

    @Column(name = "picture_path")
    private String picture_path;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "seller_id")
    private Integer seller_id;

    public Products() {
    }

    public Products(int productId, String productName, String desc, String price, String category, String brand, String sku, String type, String picture_path, Integer discount, Integer stock,Integer seller_id) {
        this.productId = productId;
        this.productName = productName;
        this.desc = desc;
       this.price=price;
        this.category = category;
        this.brand = brand;
        this.sku = sku;
        this.type = type;
        this.picture_path = picture_path;
        this.discount = discount;
        this.stock = stock;
        this.seller_id=seller_id;
    }

    public Products(Products p){
        this.productId = p.getProductId();
        this.productName = p.getProductName();
        this.desc = p.getDesc();
        this.price=p.getPrice();
        this.category = p.getCategory();
        this.brand = p.getBrand();
        this.sku = p.getSku();
        this.type = p.getType();
        this.picture_path = p.getPicture_path();
        this.discount = p.getDiscount();
        this.stock = p.getStock();
        this.seller_id=p.getSeller_id();

    }

    public Products(String productName, String picture_path, Integer discount) {
        this.productName = productName;
        this.picture_path = picture_path;
        this.discount = discount;
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Integer seller_id) {
        this.seller_id = seller_id;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", desc='" + desc + '\'' +

                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", sku='" + sku + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
