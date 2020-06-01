package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;

import com.example.sagar.SpringSecurityWithJWT.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/getCartList/{userId}" ,method = RequestMethod.GET)
    public List<Products> getAllProducts(@PathVariable Integer userId)
    {

        return cartService.getCartList(userId);

    }

    @RequestMapping(value = "/addToCartList" ,method = RequestMethod.POST)
    public String addToCartList(@RequestBody Carts carts)
    {
       cartService.addToCartList(carts);

        return "Added to cart";
    }

    @RequestMapping(value = "/removeFromCart" ,method = RequestMethod.DELETE)
    public String removeFromCartList(@RequestBody Carts carts)
    {
        cartService.removeFromCartList(carts);
        return "Removed from cart";
    }

    @RequestMapping(value = "/getBadgeCount/{userId}" ,method = RequestMethod.GET)
    public String getBadgeCount(@PathVariable Integer userId)
    {
             int count=cartService.getBadgeCount(userId);

        return "{'numrows':"+count+"}" ;
    }


}
