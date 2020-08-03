package com.example.sagar.SpringSecurityWithJWT.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "coupons")
@Entity(name = "coupons")

@NoArgsConstructor
@Getter
@Setter
public class Coupons {

    @Id
    @Column(name = "id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "product_id")
    private Integer product_id;
    @Column(name = "coupon_code")
    private String coupon_code;
    @Column(name = "discount")
    private Integer discount;


}
