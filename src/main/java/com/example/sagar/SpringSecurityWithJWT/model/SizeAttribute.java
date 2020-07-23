package com.example.sagar.SpringSecurityWithJWT.model;

import javax.persistence.*;

@Entity(name = "size_attribute")
@Table(name = "size_attribute")
public class SizeAttribute {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private Integer product_id;
    @Column(name = "size")
    private String size;

    public SizeAttribute(Integer product_id, String size) {
        this.product_id = product_id;
        this.size = size;
    }

    public SizeAttribute() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
