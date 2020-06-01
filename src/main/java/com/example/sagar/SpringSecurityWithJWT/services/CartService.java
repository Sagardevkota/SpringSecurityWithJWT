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

    public List<Products> getCartList(Integer userId){
        return cartRepository.getCartList(userId);
    }

    public void addToCartList(Carts carts)
    {
            cartRepository.save(carts);
    }


    public void removeFromCartList(Carts carts)
    {
        cartRepository.delete(carts);
    }


    public int getBadgeCount(Integer userId) {

        return cartRepository.getCartList(userId).size();
    }
}
