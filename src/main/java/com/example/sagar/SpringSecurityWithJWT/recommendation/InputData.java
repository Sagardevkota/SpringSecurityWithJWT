package com.example.sagar.SpringSecurityWithJWT.recommendation;

import com.example.sagar.SpringSecurityWithJWT.model.Reviews;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class InputData {


    public static List<Item> items;

    public static Map<User, HashMap<Item, Double>> initializeData(List<Reviews> reviewsList) {
        Map<User, HashMap<Item, Double>> data = new HashMap<>();

        items = reviewsList.stream()
                .map(reviews -> new Item(reviews.getProduct_id()))
                .distinct()
                .collect(Collectors.toList());

        List<User> users = reviewsList.stream()
                .map(reviews -> new User(reviews.getUser_id()))
                .distinct()
                .collect(Collectors.toList());

        HashMap<Item, Double> newUser;


        for (User user : users) {
            newUser = new HashMap<>();
            for (Reviews reviews : reviewsList) {
                newUser.put(new Item(reviews.getProduct_id()), Double.valueOf(reviews.getRating()));
            }
            data.put(user, newUser);
        }

        return data;
    }

}
