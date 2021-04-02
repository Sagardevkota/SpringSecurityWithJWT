package com.example.sagar.SpringSecurityWithJWT.controller;
/*
 * @created 01/{03}/2021 - 2:54 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.mapper.ProductMapper;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.ConversationService;
import com.example.sagar.SpringSecurityWithJWT.services.OrderService;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final ProductService productService;
    private final OrderService orderService;
    private final ConversationService conversationService;
    private final ProductMapper productMapper;


    @Autowired
    SellerController(ProductService productService, OrderService orderService, ConversationService conversationService, ProductMapper productMapper) {
        this.productService = productService;
        this.orderService = orderService;
        this.conversationService = conversationService;
        this.productMapper = productMapper;

    }

    @GetMapping(value = "/products")
    public List<ProductDto> getProductsOFSeller(@CurrentSecurityContext Authentication authentication) {
        return productService.getProductsOfSeller(getUserId(authentication));
    }

    //hasRole(...) set a prefix for the the content - the default one is ROLE_
    //hasAuthority(...) checks the content WITHOUT a prefix
    @PostMapping(value = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public JsonResponse addProduct(@RequestPart("products") String products, @CurrentSecurityContext Authentication authentication, @RequestPart("file") MultipartFile file) {

        try {
            //convert product string to product obj
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto = objectMapper.readValue(products, ProductDto.class);
            //get seller id from principal and set it
            productDto.setSeller_id(getUserId(authentication));

            //map dto to entity
            Products entity = productMapper.toEntity(productDto);
            //convert json list to string for size and colors
            entity.setColors(productMapper.converJsonListToString(productDto.getColors()));
            entity.setSizes(productMapper.converJsonListToString(productDto.getSizes()));
            entity.setPicturePath(entity.getSku() + "_IMG.jpg"); //sku is unique for every product
            productService.addProduct(entity);
            String response = productService.saveImage(file, entity.getSku() + "_IMG.jpg");
            return new JsonResponse("200 Ok", "Product Added successfully");


        } catch (Exception e) {
            return new JsonResponse("500 Internal error", e.getMessage());
        }


    }


    @PutMapping(value = "/products", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public JsonResponse updateProduct(@RequestPart("products") String products, @CurrentSecurityContext Authentication authentication, @RequestPart("file") MultipartFile file) {

        try {
            //convert product string to product obj
            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto products1 = objectMapper.readValue(products, ProductDto.class);
            products1.setPicturePath(products1.getSku()+"_IMG.jpg");
            products1.setSeller_id(getUserId(authentication));
            productService.saveImage(file, products1.getSku() + "_IMG.jpg");
            productService.updateProduct(products1);
            return new JsonResponse("200 OK", "Updated product");
        } catch (Exception e) {
            return new JsonResponse("500 Internal Server error", e.getMessage());
        }

    }

    @PutMapping(value = "/products/no-image")
    public JsonResponse updateProductWithOutImage(@RequestBody ProductDto products) throws JsonProcessingException {
        productService.updateProduct(products);
        return new JsonResponse("200 OK","Updated product");
    }


    @DeleteMapping(value = "/products/{productId}")
    public JsonResponse deleteProduct(@PathVariable Integer productId) {

        //now delete the product
        productService.deleteProduct(productId);
        return new JsonResponse("200 OK", "Deleted the item");
    }


    @GetMapping(value = "/name")
    public JsonResponse getSellerName(@CurrentSecurityContext Authentication authentication) {
        String userName = getUserName(authentication);
        return new JsonResponse("200 OK", userName);
    }

    private int getUserId(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }

    private String getUserName(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getUsername();
    }

    @GetMapping(value = "/orders/status/{status}")
    public List<OrderDto> getOrdersForSeller(@CurrentSecurityContext Authentication authentication, @PathVariable String status) {
        return orderService.getOrdersForSellers(getUserId(authentication), status);
    }


    //get order count for a seller
    @GetMapping(value = "/orders/count/{status}")
    public JsonResponse getOrderCount(@CurrentSecurityContext Authentication authentication, @PathVariable String status) {
        return new JsonResponse("200 Ok", String.valueOf(orderService.countOrder(getUserId(authentication), status)));
    }

    //change status of orders
    @PutMapping(value = "/orders/{orderId}/status/{status}")
    public JsonResponse changeStatus(@PathVariable Integer orderId, @PathVariable String status) throws MessagingException, IOException, TemplateException {
        orderService.changeStatus(orderId, status);
        return new JsonResponse("200 Ok", "Changed status to " + status);
    }

    //change ordered date of status
    @PutMapping(value = "/orders/{orderId}/date/{orderedDate}")
    public JsonResponse changeOrderedDate(@PathVariable Integer orderId, @PathVariable String orderedDate) {
        orderService.changeOrderedDate(orderId, orderedDate);
        return new JsonResponse("200 Ok", "Changed ordered date to " + orderedDate);
    }


    @GetMapping(value = "/conversations")
    public List<MessageDto> getConversationList(@CurrentSecurityContext Authentication authentication) {
        return conversationService.getConversationList(getUserId(authentication));
    }

    @GetMapping(value = "/conversations/{productId}")
    public List<ConversationDto> getConversation(@PathVariable Integer productId) {
        return conversationService.getConversations(productId);
    }
}
