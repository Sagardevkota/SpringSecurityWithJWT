package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "size_attribute")
@Table(name = "size_attribute")
@Getter
@Setter
@NoArgsConstructor

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


}
