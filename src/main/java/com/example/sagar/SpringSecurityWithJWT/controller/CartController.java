package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.CartDto;
import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/carts")
    public List<CartDto> getAllProducts(@CurrentSecurityContext Authentication authentication) {
        return cartService.getCartList(getUserId(authentication));
    }

    @PostMapping(value = "/carts")
    public JsonResponse addToCartList(@RequestBody Carts carts) {
        if (cartService.addToCartList(carts).equalsIgnoreCase("Item is already in cart"))
            return new JsonResponse("401 Conflict", "Item is already in cart");
        else
            return new JsonResponse("200 OK", "Added to cart");
    }

    @DeleteMapping(value = "/carts")
    public JsonResponse removeFromCartList(@RequestBody Carts carts) {
        cartService.removeFromCartList(carts);
        return new JsonResponse("200 Ok", "Item is removed from cart");
    }

    @GetMapping(value = "/carts/count")
    public JsonResponse getBadgeCount(@CurrentSecurityContext Authentication authentication) {
        int count = cartService.getBadgeCount(getUserId(authentication));
        return new JsonResponse("200 OK", String.valueOf(count));
    }


    private int getUserId(Authentication authentication){
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }

}
