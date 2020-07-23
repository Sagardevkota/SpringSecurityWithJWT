package com.example.sagar.SpringSecurityWithJWT.model;



import javax.persistence.*;


@Entity(name = "user_info")
@Table(name = "user_info")
public class User {
   @Id
    @Column(name = "user_id",nullable = false,unique = true)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
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

    //for registration
    public User(String userName, String password, String deliveryAddress, String phone, String role,String age,String gender,Double latitude,Double longitude) {
        this.userName = userName;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.role=role;
        this.age=age;
        this.gender=gender;
        this.latitude=latitude;
        this.longitude=longitude;
    }


    //for user details
    public User(Integer id, String userName, String deliveryAddress, String phone,String age
                ,String gender,String role) {
        this.userName = userName;
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.gender=gender;
        this.age=age;
        this.phone = phone;
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
