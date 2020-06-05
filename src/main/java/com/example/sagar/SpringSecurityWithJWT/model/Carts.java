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

    public Carts() {
    }

    public Carts(Integer user_id, Integer product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
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
