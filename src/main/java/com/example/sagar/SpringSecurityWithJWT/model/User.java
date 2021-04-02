package com.example.sagar.SpringSecurityWithJWT.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotEmpty(message = "Username field can not be empty or missed")
    @Size(min = 5, max = 128, message = "Username field must have from 5 to 128 symbols")
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "Password field cant be empty")
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @NotEmpty(message = "Phone must not be empty")
    @Size(min = 9,max = 10,message = "Phone should have 10 characters")
    @Column(name = "phone")
    private String phone;

    @NotEmpty(message = "Age cant be empty")
    @Column(name = "age")
    private String age;

    @NotEmpty(message = "Gender cant be empty")
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

    //for registration
    public User(String userName, String password, String deliveryAddress, String phone, String role, String age, String gender, Double latitude, Double longitude) {
        this.userName = userName;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.role = role;
        this.age = age;
        this.gender = gender;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
