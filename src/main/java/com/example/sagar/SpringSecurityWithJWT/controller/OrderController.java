package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.example.sagar.SpringSecurityWithJWT.services.OrderService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order/id/{userId}/status/{status}", method = RequestMethod.GET)
    public List<OrderResponse> getOrdersResponse(@PathVariable  Integer userId,@PathVariable String status){

        return orderService.getOrdersResponse(userId,status);

    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public JsonResponse getOrdersResponse(@RequestBody Order order){

        try {

          Integer stock=orderService.getStock(order.getProductId());
          Integer decreasedStock=stock-order.getQty();
           orderService.changeStock(decreasedStock,order.getProductId());
           orderService.addOrders(order);

           return new JsonResponse("200 OK","Added to order");
       }
       catch (Exception e){

           return new JsonResponse("409 Conflict",e.getMessage());

       }



    }

    @RequestMapping(value = "/order/seller/id/{sellerId}/status/{status}", method = RequestMethod.GET)
    public List<OrderResponse> getOrdersForSeller(@PathVariable  Integer sellerId,@PathVariable String status){

        List<OrderResponse> orderResponses=orderService.getOrdersForSellers(sellerId,status);
        return orderResponses;

    }

    //get order count for a seller
    @RequestMapping(value = "/order/seller/count/{sellerId}/{status}", method = RequestMethod.GET)
    public JsonResponse getOrderCount(@PathVariable Integer sellerId,@PathVariable String status){
        return new JsonResponse("200 Ok",String.valueOf(orderService.countOrder(sellerId,status)));
    }


    //change status of orders
    @RequestMapping(value = "/order/{orderId}/status/{status}", method = RequestMethod.PUT)
    public JsonResponse changeStatus(@PathVariable Integer orderId,@PathVariable String status){
        orderService.changeStatus(orderId,status);
        return new JsonResponse("200 Ok","Changed status to "+status);
    }

    //change ordered date of status
    @RequestMapping(value = "/order/{orderId}/date/{orderedDate}", method = RequestMethod.PUT)
    public JsonResponse changeOrderedDate(@PathVariable Integer orderId,@PathVariable String orderedDate){
        orderService.changeOrderedDate(orderId,orderedDate);
        return new JsonResponse("200 Ok","Changed ordered date to "+orderedDate);
    }





}
