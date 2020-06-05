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

    @Column(name = "verified")
    private Boolean verified;

    @Column(name = "phone")
    private String phone;

    public User(String userName, String password, String deliveryAddress, String phone,Boolean verified) {
        this.userName = userName;
        this.password = password;
        this.deliveryAddress = deliveryAddress;
        this.phone = phone;
        this.verified=verified;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean getVerifed() {
        return verified;
    }

    public void setVerifed(Boolean verifed) {
        this.verified = verified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
