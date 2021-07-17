package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.EmailService;
import com.example.sagar.SpringSecurityWithJWT.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@RestController
public class OrderController {

    private final OrderService orderService;

    private final Queue<Order> msgQueue = new LinkedList<>();

    @Autowired
    private EmailService emailService;


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

    @PostMapping(value = "/multiple-orders")
    public JsonResponse addMultipleOrders(@RequestBody OrderWrapper orderWrapper){
        msgQueue.clear();
        orderWrapper.getOrders().forEach(this::getOrdersResponse);
        msgQueue.forEach(order -> {
            try {
                emailService.sendEmail(order,"ORDER_CONFIRMATION");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return new JsonResponse("200 OK","Added orders");
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
                msgQueue.add(order);

                return new JsonResponse("200 OK", "Added to order");

            } catch (Exception e) {
                return new JsonResponse("409 Conflict", e.getMessage());
            }
        }


    }


}
