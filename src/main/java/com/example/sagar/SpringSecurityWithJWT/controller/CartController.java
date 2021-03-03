package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.CartDto;
import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/cart/{userId}")
    public List<CartDto> getAllProducts(@PathVariable Integer userId) {
        return cartService.getCartList(userId);
    }

    @PostMapping(value = "/cart")
    public JsonResponse addToCartList(@RequestBody Carts carts) {
        if (cartService.addToCartList(carts).equalsIgnoreCase("Item is already in cart"))
            return new JsonResponse("401 Conflict", "Item is already in cart");
        else
            return new JsonResponse("200 OK", "Added to cart");
    }

    @DeleteMapping(value = "/cart")
    public JsonResponse removeFromCartList(@RequestBody Carts carts) {
        cartService.removeFromCartList(carts);
        return new JsonResponse("200 Ok", "Item is removed from cart");
    }

    @GetMapping(value = "/cart/count/{userId}")
    public JsonResponse getBadgeCount(@PathVariable Integer userId) {
        int count = cartService.getBadgeCount(userId);
        return new JsonResponse("200 OK", String.valueOf(count));
    }


}
