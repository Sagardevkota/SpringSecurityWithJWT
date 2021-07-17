package com.example.sagar.SpringSecurityWithJWT.model;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OrderWrapper {

    private List<Order> orders = new ArrayList<>();
}
