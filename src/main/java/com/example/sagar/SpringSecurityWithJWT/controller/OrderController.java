package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.example.sagar.SpringSecurityWithJWT.model.OrderDto;
import com.example.sagar.SpringSecurityWithJWT.model.ProductDto;
import com.example.sagar.SpringSecurityWithJWT.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders/status/{status}")
    public List<OrderDto> getOrdersResponse(@CurrentSecurityContext Authentication authentication, @PathVariable String status) {
        return orderService.getOrdersResponse(getUserId(authentication), status);

    }


    private int getUserId(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }

    @PostMapping(value = "/orders")
    public JsonResponse getOrdersResponse(@RequestBody Order order) {

        synchronized (this){
            try {
                Integer stock = orderService.getStock(order.getProductId());
                if (stock==0)
                    throw new Exception("Sorry the product is not in stock");

                Integer decreasedStock = stock - order.getQuantity();
                orderService.changeStock(decreasedStock, order.getProductId());
                orderService.addOrders(order);
                return new JsonResponse("200 OK", "Added to order");

            } catch (Exception e) {
                return new JsonResponse("409 Conflict", e.getMessage());
            }
        }


    }


}
