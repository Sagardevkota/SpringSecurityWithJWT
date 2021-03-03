package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private int productId;
    private String productName;
    private String desc;
    private String price;
    private String category;
    private String brand;
    private String sku;
    private String type;
    private String picturePath;
    private Integer discount;
    private Integer stock;
    private Integer seller_id;
    private String rating;

}
