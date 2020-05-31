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
    @Column(name = "desc")
    private String desc;
    @Column(name = "marked_price")
    private String marked_price;
    @Column(name = "fixed_price")
    private String fixed_price;
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

    public Products() {
    }

    public Products(String productName, String desc, String marked_price, String fixed_price, String category, String brand, String sku, String type,String picture_path) {
        this.productName = productName;
        this.desc = desc;
        this.marked_price = marked_price;
        this.fixed_price = fixed_price;
        this.category = category;
        this.brand = brand;
        this.sku = sku;
        this.type = type;
        this.picture_path=picture_path;
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

    public String getMarked_price() {
        return marked_price;
    }

    public void setMarked_price(String marked_price) {
        this.marked_price = marked_price;
    }

    public String getFixed_price() {
        return fixed_price;
    }

    public void setFixed_price(String fixed_price) {
        this.fixed_price = fixed_price;
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

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", desc='" + desc + '\'' +
                ", marked_price='" + marked_price + '\'' +
                ", fixed_price='" + fixed_price + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", sku='" + sku + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
