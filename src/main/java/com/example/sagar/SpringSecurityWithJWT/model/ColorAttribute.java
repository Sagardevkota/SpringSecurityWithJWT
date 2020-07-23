package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;

@Entity(name = "color_attribute")
@Table(name = "color_attribute")
public class ColorAttribute {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private Integer product_id;
    @Column(name = "color")
    private String color;

    public ColorAttribute(Integer product_id, String color) {
        this.product_id = product_id;
        this.color = color;
    }

    public ColorAttribute() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
