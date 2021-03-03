package com.example.sagar.SpringSecurityWithJWT.controller;
/*
 * @created 01/{03}/2021 - 2:54 PM
 * @project SpringSecurityWithJWT
 * @author SAGAR DEVKOTA
 */

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.OrderService;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import com.example.sagar.SpringSecurityWithJWT.services.UserService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    private final ProductService productService;
    private final UserService userService;
    private final OrderService orderService;

    @Autowired
    SellerController(ProductService productService, UserService userService, OrderService orderService){
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/products")
    public List<Products> getProductsOFSeller(@CurrentSecurityContext Authentication authentication) {
        return productService.getProductsOfSeller(getUserId(authentication));
    }

    //hasRole(...) set a prefix for the the content - the default one is ROLE_
    //hasAuthority(...) checks the content WITHOUT a prefix
    @PostMapping(value = "/products")
    public JsonResponse addProduct(@Valid @RequestBody Products products) {

        try {
            productService.addProduct(products);
            Integer product_id = productService.getProductId(products.getProductName());
            return new JsonResponse("200 Ok", String.valueOf(product_id));

        } catch (Exception e) {
            return new JsonResponse("500 Internal error", e.getMessage());
        }


    }


    @PutMapping(value = "/products")
    public JsonResponse updateProduct(@RequestBody Products products) {
        try {
            productService.updateProduct(products);
            return new JsonResponse("200 OK", "Updated product");
        } catch (Exception e) {
            return new JsonResponse("500 Internal Server error", e.getMessage());
        }

    }


    @DeleteMapping(value = "/product/{productId}")
    public JsonResponse deleteProduct(@PathVariable Integer productId) {

        //first clear all related info or else foreign key constraint fails will occur
        productService.clearSizes(productId);
        productService.clearColors(productId);

        //now delete the product
        productService.deleteProduct(productId);
        return new JsonResponse("200 OK", "Deleted the item");
    }


    @GetMapping(value = "/name")
    public JsonResponse getSellerName(@CurrentSecurityContext Authentication authentication){
        String userName = getUserName(authentication);
        return new JsonResponse("200 OK",userName);
    }

    private int getUserId(Authentication authentication){
        UserPrincipal user =(UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }

    private String getUserName(Authentication authentication){
        UserPrincipal user =(UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getUsername();
    }

    @GetMapping(value = "/orders/status/{status}")
    public List<OrderDto> getOrdersForSeller(@CurrentSecurityContext Authentication authentication, @PathVariable String status){
        return orderService.getOrdersForSellers(getUserId(authentication),status);
    }


    //get order count for a seller
    @GetMapping(value = "/orders/count/{status}")
    public JsonResponse getOrderCount(@CurrentSecurityContext Authentication authentication,@PathVariable String status){
        return new JsonResponse("200 Ok",String.valueOf(orderService.countOrder(getUserId(authentication),status)));
    }

    //change status of orders
    @PutMapping(value = "/orders/{orderId}/status/{status}")
    public JsonResponse changeStatus(@PathVariable Integer orderId,@PathVariable String status) throws MessagingException, IOException, TemplateException {
        orderService.changeStatus(orderId,status);
        return new JsonResponse("200 Ok","Changed status to "+status);
    }

    //change ordered date of status
    @PutMapping(value = "/orders/{orderId}/date/{orderedDate}")
    public JsonResponse changeOrderedDate(@PathVariable Integer orderId,@PathVariable String orderedDate){
        orderService.changeOrderedDate(orderId,orderedDate);
        return new JsonResponse("200 Ok","Changed ordered date to "+orderedDate);
    }

    @PostMapping(value = "/colors")
    public JsonResponse addColor(@RequestBody ColorAttribute colorAttribute) {
        try {
            String color = colorAttribute.getColor();
            if (!color.isEmpty()) {
                productService.clearColors(colorAttribute.getProduct_id());
                String[] colorList = color.split(",");
                for (String color1 : colorList) {
                    ColorAttribute colorAttribute1 = new ColorAttribute(colorAttribute.getProduct_id(), color1.trim());
                    productService.addColor(colorAttribute1);
                }
            }

            return new JsonResponse("200 OK", "Color added");
        } catch (Exception e) {
            return new JsonResponse("500 Internal Server error", e.getMessage());
        }

    }

    @PostMapping(value = "/sizes")
    public JsonResponse addColor(@RequestBody SizeAttribute sizeAttribute) {
        try {

            String size = String.valueOf(sizeAttribute.getSize());
            if (!size.isEmpty()) {
                productService.clearSizes(sizeAttribute.getProduct_id());
                String[] sizeList = size.split(",");
                for (String size1 : sizeList) {
                    SizeAttribute sizeAttribute1 = new SizeAttribute(sizeAttribute.getProduct_id(), size1);
                    productService.addSize(sizeAttribute1);
                }
            }

            return new JsonResponse("200 OK", "Size added");
        } catch (Exception e) {
            return new JsonResponse("500 Internal Server error", e.getMessage());
        }

    }


    @PostMapping(value = "/image")
    public JsonResponse uploadImage(@RequestParam("image") MultipartFile image) {
        return productService.saveImage(image);
    }
}
