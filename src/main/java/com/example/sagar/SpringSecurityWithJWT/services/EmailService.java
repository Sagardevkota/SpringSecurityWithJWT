package com.example.sagar.SpringSecurityWithJWT.services;
/*
 * @created 16/{07}/2021 - 4:19 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.model.Order;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    @Lazy
    @Autowired
    private JavaMailSender sender;

    @Lazy
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private ProductService productService;

    @Lazy
    @Autowired
    private Configuration config;

    @Async("processExecutor")
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
