package com.example.sagar.SpringSecurityWithJWT.model;



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

    public ProductResponse(int productId, String productName, String desc, String price, String category, String brand, String sku, String type, String picture_path, Integer discount, Integer stock, Integer seller_id,String rating) {
        this.productId = productId;
        this.productName = productName;
        this.desc = desc;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.sku = sku;
        this.type = type;
        this.picture_path = picture_path;
        this.discount = discount;
        this.stock = stock;
        this.seller_id = seller_id;
        this.rating=rating;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public String getSku() {
        return sku;
    }

    public String getType() {
        return type;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Integer getStock() {
        return stock;
    }



    public String getRating() {
        return rating;
    }

    public Integer getSeller_id() {
        return seller_id;
    }
}
