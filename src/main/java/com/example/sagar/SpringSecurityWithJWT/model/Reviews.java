package com.example.sagar.SpringSecurityWithJWT.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reviews")
@Entity(name = "reviews")
public class Reviews {

    @Id
    @Column(name = "id",unique = true,nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "product_id")
    private Integer product_id;

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private String rating;

    @Column(name = "date")
    private String date;


}
