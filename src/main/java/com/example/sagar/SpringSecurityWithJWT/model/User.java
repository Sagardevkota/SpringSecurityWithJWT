package com.example.sagar.SpringSecurityWithJWT.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity(name = "user_info")
@Table(name = "user_info")
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "delivery_address")
    private String deliveryAddress;


    @Column(name = "phone")
    private String phone;

    @Column(name = "age")
    private String age;


    @Column(name = "gender")
    private String gender;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "role")
    private String role;

    @Transient
    private int cartCount;




}
