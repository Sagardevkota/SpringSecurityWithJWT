package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Setter
@ToString
@Entity(name = "products")
@Table(name = "products")
@Getter
@NoArgsConstructor
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



}
