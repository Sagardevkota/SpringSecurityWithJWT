package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.mapper.OrderMapper;
import com.example.sagar.SpringSecurityWithJWT.model.OrderDto;
import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.OrderRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EmailService emailService;



    public List<OrderDto> getOrdersResponse(Integer userId, String status) {
        List<Order> orders = getOrders(userId, status);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order o : orders) {
            Integer product_id = o.getProductId();
            List<Products> products = productRepository.findAllByProductId(product_id);
            Products singleProduct = products.get(0);
            OrderDto orderDto = orderMapper.toDto(o);
            orderMapper.setRemainingField(orderDto,
                    userService.getUserName(o.getUserId()),
                    singleProduct.getProductName(),
                    singleProduct.getPicturePath(),
                    userService.getPhone(o.getUserId()),
                    singleProduct.getDiscount()
            );
            orderDtoList.add(orderDto);
        }


        return orderDtoList;

    }


    public List<Order> getOrders(Integer userId, String status) {
        return orderRepository.getOrders(userId, status);

    }


    public void addOrders(Order order) {
        orderRepository.save(order);
    }

    public List<OrderDto> getOrdersForSellers(Integer seller_id, String status) {
        List<Order> orders = orderRepository.getOrdersForSeller(seller_id, status);
        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order o : orders) {
            Integer product_id = o.getProductId();
            List<Products> products = productService.findAllByProductId(product_id);
            Products singleProduct = products.get(0);
            OrderDto orderDto = orderMapper.toDto(o);
            orderMapper.setRemainingField(orderDto,
                    userService.getUserName(o.getUserId()),
                    singleProduct.getProductName(),
                    singleProduct.getPicturePath(),
                    userService.getPhone(o.getUserId()),
                    singleProduct.getDiscount()
            );
            orderDtoList.add(orderDto);

        }

        return orderDtoList;


    }


    public Integer countOrder(Integer seller_id, String status) {
        return orderRepository.getOrdersForSeller(seller_id, status).size();
    }

    public void changeStatus(Integer orderId, String status) throws MessagingException, TemplateException, IOException {
        orderRepository.changeStatus(orderId, status);
        Order order = orderRepository.getOne(orderId);
        emailService.sendEmail(order,status.toUpperCase(Locale.ROOT) );
    }

    public void changeOrderedDate(Integer orderId, String deliveredDate) {
        orderRepository.changeOrderedDate(orderId, deliveredDate);
    }

    public void changeStock(Integer qty, Integer productId) {
        productRepository.changeStock(qty, productId);
    }

    public Integer getStock(Integer productId) {
        return productRepository.getStock(productId);
    }




}
