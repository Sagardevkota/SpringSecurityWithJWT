package com.example.sagar.SpringSecurityWithJWT.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "carts")
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
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

    //add @Transient to have non db field


}
