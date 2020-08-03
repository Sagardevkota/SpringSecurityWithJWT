package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.OrderResponse;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private JavaMailSender sender;

    @Autowired
    private Configuration config;

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




    public void addOrders(Order order) throws MessagingException, IOException, TemplateException {

        orderRepository.save(order);
        sendEmail(order);

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



    public void sendEmail(Order order) throws MessagingException, IOException, TemplateException {

        String userName=userService.getUserName(order.getUserId());
        String productName=productService.getProductName(order.getProductId());

        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model=new HashMap<>();
        model.put("userName",userName);
        model.put("productName",productName);
        model.put("qty",order.getQty());
        model.put("color",order.getProductColor());
        model.put("size",order.getProductSize());
        model.put("address",order.getDeliveryAddress());
        model.put("price",order.getPrice());



            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("robot.png"));

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(userName);
            helper.setText(html, true);
            helper.setSubject("Order Confirmation");
            helper.setFrom("noreply@SMart.com");
            sender.send(message);


    }

}
