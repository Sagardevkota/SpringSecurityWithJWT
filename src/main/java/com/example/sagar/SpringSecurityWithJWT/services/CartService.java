package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.CartResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<CartResponse> getCartList(Integer userId)
    {
        List<Products> products=cartRepository.getCartList(userId);
        List<CartResponse> cartResponseList=new ArrayList<>();
        for (Products p:products){
            String color=cartRepository.getcolor(p.getProductId(),userId);
            Float size=cartRepository.getSize(p.getProductId(),userId);
            String price=cartRepository.getPrice(p.getProductId(),userId);
            if (color==null)
                color="";
            if (size==0)
                size=0f;
            System.out.println(p.toString());
            cartResponseList.add(new CartResponse(p.getProductId(),
                    p.getProductName(),
                    p.getDesc(),

                    price,
                    p.getCategory(),
                    p.getBrand(),
                    p.getSku(),
                    p.getType(),
                    p.getPicture_path(),
                    p.getDiscount(),
                   p.getStock(),
                   color,
                    size

                    ));
        }
        return cartResponseList;
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
