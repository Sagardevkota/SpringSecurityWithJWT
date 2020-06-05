package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Products> getCartList(Integer userId)
    {
        return cartRepository.getCartList(userId);
    }

    public String addToCartList(Carts carts)
    {
        if (checkIfCartItemExists(carts).size()>0){
            return "Item is already in cart";
        }
        else {
            cartRepository.save(carts);
            return "Added to cart";
        }


    }

    public List<Carts> checkIfCartItemExists(Carts carts){
       return cartRepository.findAllByProduct_idAndUser_id(carts.getProduct_id(),carts.getUser_id());
    }


    public void removeFromCartList(Carts carts)
    {
        cartRepository.deleteFromCart(carts.getProduct_id(),carts.getUser_id());
    }


    public int getBadgeCount(Integer userId)
    {
        return cartRepository.getCartList(userId).size();
    }
}
