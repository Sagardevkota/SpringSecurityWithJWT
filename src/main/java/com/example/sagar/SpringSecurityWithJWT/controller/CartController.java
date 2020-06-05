package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
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
    public JsonResponse addToCartList(@RequestBody Carts carts)
    {
      if (cartService.addToCartList(carts).equalsIgnoreCase("Item is already in cart"))
       return new JsonResponse("401 Conflict","Item is already in cart");
      else
          return new JsonResponse("200 OK","Added to cart");
    }

    @RequestMapping(value = "/removeFromCart" ,method = RequestMethod.DELETE)
    public JsonResponse removeFromCartList(@RequestBody Carts carts)
    {
        cartService.removeFromCartList(carts);
        return new JsonResponse("200 Ok","Item is removed from cart");

    }

    @RequestMapping(value = "/getBadgeCount/{userId}" ,method = RequestMethod.GET)
    public JsonResponse getBadgeCount(@PathVariable Integer userId)
    {
             int count=cartService.getBadgeCount(userId);

        return new JsonResponse("200 OK",String.valueOf(count)) ;
    }


}
