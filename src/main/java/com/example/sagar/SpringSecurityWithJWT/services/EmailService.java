package com.example.sagar.SpringSecurityWithJWT.services;
/*
 * @created 16/{07}/2021 - 4:19 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.exception.InvalidException;
import com.example.sagar.SpringSecurityWithJWT.model.Email;
import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class EmailService {

//    @Lazy
//    @Autowired
//    private JavaMailSender sender;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private ProductService productService;

    @Lazy
    @Autowired
    private Configuration config;

    @Lazy
    @Autowired
    private RestTemplate restTemplate;

    private static final String EMAIL_URL = "https://1gnwv20z36.execute-api.us-east-2.amazonaws.com/sendEmail";

    @Async("processExecutor")
    public void sendEmail(Order order, String mailType) throws IOException, TemplateException {

        String userName = userService.getUserName(order.getUserId());
        String productName = productService.getProductName(order.getProductId());

//        MimeMessage message = sender.createMimeMessage();
        Map<String, Object> model = new HashMap<>();
        model.put("userName", userName);
        model.put("productName", productName);
        model.put("qty", order.getQuantity());
        model.put("color", order.getProductColor());
        model.put("size", order.getProductSize());
        model.put("address", order.getDeliveryAddress());
        model.put("price", order.getPrice());
        model.put("status", order.getStatus());


         //set mediaType
//        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
//                StandardCharsets.UTF_8.name());
//         //add attachment
//            helper.addAttachment("logo.png", new ClassPathResource("robot.png"));
//
        Template t = config.getTemplate("email-template.ftl");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//
//        helper.setTo(userName);
//        helper.setText(html, true);
//        helper.setSubject(mailType);
//        helper.setFrom("noreply@SMart.com");

//        {
//            "to": "something@gces.edu.np",
//                "subject": "This is test from Azure",
//                "text": "<h1>Hello test</h1><p>This is next paragraph form postman</p>"
//        }



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Email email = Email.builder()
                .to(userName)
                .subject(mailType)
                .text(html)
                .build();


        ObjectMapper objectMapper = new ObjectMapper();
        try {

            String jsonStr = objectMapper.writeValueAsString(email);
            HttpEntity<String> entity = new HttpEntity<>(jsonStr, headers);
            String response = restTemplate.postForObject(EMAIL_URL, entity,String.class);
            log.info(response);
        }

        catch (Exception e){
            log.error("EMAIL SEND: "+ e.getCause());
        }



    }
}
