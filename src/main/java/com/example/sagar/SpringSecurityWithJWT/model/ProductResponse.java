package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponse {

    private int productId;
    private String productName;
    private String desc;
    private String price;
    private String category;
    private String brand;
    private String sku;
    private String type;
    private String picture_path;
    private Integer discount;
    private Integer stock;
    private Integer seller_id;
    private String rating;

}
