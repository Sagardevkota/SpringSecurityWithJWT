package com.example.sagar.SpringSecurityWithJWT.model;

import com.example.sagar.SpringSecurityWithJWT.controller.OrderResponse;
import com.example.sagar.SpringSecurityWithJWT.repository.OrderRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public List<OrderResponse> getOrdersResponse(Integer userId,String status){
        List<Order> orders=getOrders(userId,status);
        List<OrderResponse> orderResponses=new ArrayList<>();

        for (Order o:orders){
            Integer product_id=o.getProductId();
            List<Products> products=productRepository.findAllByProductId(product_id);
            Products singleProduct=products.get(0);
            orderResponses.add(new OrderResponse(
                    o.getOrderId(),
                    o.getProductId(),
                    o.getProductColor(),
                    o.getProductSize(),
                    o.getPrice(),
                    o.getQty(),
                    o.getOrderedDate(),
                    o.getDelivered_date(),
                    o.getDeliveryAddress(),
                    o.getStatus(),
                    singleProduct.getProductName(),
                    singleProduct.getDiscount(),
                    singleProduct.getPicture_path()
            ));
        }



        return  orderResponses;

    }




    public List<Order> getOrders(Integer userId,String status){
        return   orderRepository.getOrders(userId,status);

    }




    public void addOrders(Order order) {
        orderRepository.save(order);
    }

    public List<OrderResponse> getOrdersForSellers(Integer seller_id,String status){
     List<Order> orders=   orderRepository.getOrdersForSeller(seller_id,status);
     List<OrderResponse> orderResponses=new ArrayList<>();

     for (Order o:orders){
            Integer product_id=o.getProductId();
            List<Products> products=productRepository.findAllByProductId(product_id);
            Products singleProduct=products.get(0);
            orderResponses.add(new OrderResponse(
                    o.getOrderId(),
                    userService.getUserName(o.getUserId()),
                    userService.getPhone(o.getUserId()),
                    o.getProductId(),
                    o.getProductColor(),
                    o.getProductSize(),
                    o.getPrice(),
                    o.getQty(),
                    o.getOrderedDate(),
                    o.getDelivered_date(),
                    o.getDeliveryAddress(),
                    o.getStatus(),
                    singleProduct.getProductName(),
                    singleProduct.getDiscount(),
                    singleProduct.getPicture_path()
            ));
        }

        return orderResponses;


    }

    public Integer countOrder(Integer seller_id, String status) {
        return orderRepository.getOrdersForSeller(seller_id,status).size();
    }

    public void changeStatus(Integer orderId, String status) {
        orderRepository.changeStatus(orderId,status);
    }

    public void changeOrderedDate(Integer orderId, String deliveredDate) {
        orderRepository.changeOrderedDate(orderId,deliveredDate);
    }

    public void changeStock(Integer qty, Integer productId) {
        productRepository.changeStock(qty,productId);
    }

    public Integer getStock(Integer productId) {
        return productRepository.getStock(productId);
    }
}
