package com.example.sagar.SpringSecurityWithJWT.model;



import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity(name = "carts")
@Table(name = "carts")
public class Carts {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "product_color")
    private String color;

    @Column(name = "product_size")
    private String size;

    @Column(name = "price")
    private String price;

    public Carts() {
    }

    public Carts(Integer user_id, Integer product_id, String color, String size,String price) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.color = color;
        this.size = size;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }
}
