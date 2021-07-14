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
    private OrderMapper orderMapper;

    @Autowired
    private Configuration config;

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


    public void addOrders(Order order) throws MessagingException, IOException, TemplateException {
        orderRepository.save(order);
        sendEmail(order, "ORDER CONFIRMATION");

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

    public void changeStatus(Integer orderId, String status) throws MessagingException, IOException, TemplateException {
        orderRepository.changeStatus(orderId, status);
        sendEmail(orderRepository.getOne(orderId), status.toUpperCase());
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


    public void sendEmail(Order order, String mailType) throws MessagingException, IOException, TemplateException {

        String userName = userService.getUserName(order.getUserId());
        String productName = productService.getProductName(order.getProductId());

        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = new HashMap<>();
        model.put("userName", userName);
        model.put("productName", productName);
        model.put("qty", order.getQuantity());
        model.put("color", order.getProductColor());
        model.put("size", order.getProductSize());
        model.put("address", order.getDeliveryAddress());
        model.put("price", order.getPrice());
        model.put("status", order.getStatus());


        // set mediaType
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        // add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("robot.png"));

        Template t = config.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

        helper.setTo(userName);
        helper.setText(html, true);
        helper.setSubject(mailType);
        helper.setFrom("noreply@SMart.com");
        sender.send(message);


    }

}
