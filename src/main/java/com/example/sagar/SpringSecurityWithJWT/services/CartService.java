package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.mapper.CartMapper;
import com.example.sagar.SpringSecurityWithJWT.model.CartDto;
import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.CartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service

public class CartService {

    private final CartRepository cartRepository;

    private final CartMapper cartMapper;
    @Autowired
    CartService(CartRepository cartRepository, CartMapper cartMapper){
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    public List<CartDto> getCartList(Integer userId)
    {
        List<Products> products=cartRepository.getCartList(userId);
        List<CartDto> cartDtoList =new ArrayList<>();
        for (Products p:products){
            String color=cartRepository.getcolor(p.getProductId(),userId);
            Float size=cartRepository.getSize(p.getProductId(),userId);
            String price=cartRepository.getPrice(p.getProductId(),userId);
            if (color==null)
                color="";
            if (size==0)
                size=0f;
            System.out.println(p.toString());
            Carts carts = Carts.builder()
                    .product_id(p.getProductId())
                    .color(color)
                    .size(String.valueOf(size))
                    .price(price)
                    .build();
            CartDto cartDto = cartMapper.toDto(carts);
            cartMapper.setRemainingField(
                    cartDto,
                    p.getBrand(),
                    p.getCategory(),
                    p.getDesc(),
                    p.getDiscount(),
                    p.getPicturePath(),
                    p.getProductName(),
                    p.getSku(),
                    p.getStock(),
                    p.getType()
                    );
            cartDtoList.add(cartDto);


        }
        return cartDtoList;
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
       return cartRepository.findAllByProduct_idAndUser_id
               (carts.getProduct_id(),carts.getUser_id());
    }


    public void removeFromCartList(Carts carts)
    {
        cartRepository.deleteFromCart(carts.getProduct_id(),carts.getUser_id());
    }


    public int getBadgeCount(Integer userId)
    {
        return cartRepository.getCount(userId);

    }
}
