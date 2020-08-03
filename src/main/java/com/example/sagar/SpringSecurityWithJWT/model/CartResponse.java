package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CartResponse {

    private int productId;

    private String productName;

    private String desc;
    private String category;

    private String brand;

    private String sku;

    private String type;


    private String picture_path;


    private Integer discount;


    private Integer stock;

    private String color;

    private float size;

    private String price;

    public CartResponse(int productId, String productName, String desc, String price, String category, String brand, String sku, String type, String picture_path, Integer discount, Integer stock, String color, float size) {
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
        this.color = color;
        this.size = size;
    }

    public CartResponse(CartResponse c){
        this.productId = c.getProductId();
        this.productName = c.getProductName();
        this.desc = c.getDesc();
        this.price=c.getPrice();
        this.category = c.getCategory();
        this.brand = c.getBrand();
        this.sku = c.getSku();
        this.type = c.getType();
        this.picture_path = c.getPicture_path();
        this.discount = c.getDiscount();
        this.stock = c.getStock();
        this.color = c.getColor();
        this.size = c.getSize();

    }


}
