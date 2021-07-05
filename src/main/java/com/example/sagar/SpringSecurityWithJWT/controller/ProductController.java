package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ProductController {

    private final ProductService productService;

    private final RestTemplate restTemplate;

    @Autowired
    ProductController(ProductService productService,  RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }



    @GetMapping(value = "/products/{pageNumber}")
    public List<ProductDto> getAllProducts(@PathVariable Integer pageNumber, @CurrentSecurityContext Authentication authentication) {
//        List<ProductDto> list;
//        list = getRecommendedProducts(getUserId(authentication));
//        //if we have no recommendation just return what is in db
//        if (list.size()==0)
//        return productService.getAllProducts(pageNumber);
//        else
//            return list;

        return productService.getAllProducts(pageNumber);

    }
    private int getUserId(Authentication authentication){
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }


    private List<ProductDto> getRecommendedProducts(int userId){
        List<ProductDto> productRespons = new ArrayList<>();
        String url = "http://localhost:9091/products/userId/{userId}";
        Map<String, Integer> vars = new HashMap<>();
        vars.put("userId", userId);
        String object = restTemplate.getForObject(url,
                String.class, vars);
        return productRespons;
    }


    @GetMapping(value = "/products/hot-deals/{pageNumber}")
    public List<ProductDto> getHotDeals(@PathVariable Integer pageNumber,@CurrentSecurityContext Authentication authentication) {
        return productService.getHotDeals(pageNumber);

    }

    @GetMapping(value = "/products/id/{id}")
    public Products getOneProduct(@PathVariable Integer id) {
        return productService.getOneProduct(id);
    }

    @GetMapping(value = "/products/{query}/{queryValue}/{sorting}")
    public List<ProductDto> getProducts(@PathVariable String query, @PathVariable String queryValue, @PathVariable String sorting) {
        List<ProductDto> products = new ArrayList<>();
        switch (query) {
            case "category":
                products = productService.getProductsByCategory(queryValue, sorting);
                break;
            case "brand":
                products = productService.getProductsByBrands(queryValue, sorting);
                break;
            case "type":
                products = productService.getProductsByType(queryValue, sorting);
                break;
        }
        return products;
    }

    @GetMapping(value = "/products/type/{type}/category/{category}/{sorting}")
    public List<ProductDto> getProductsByTypeAndCategory(@PathVariable String type, @PathVariable String category, @PathVariable String sorting) {
        List<ProductDto> products;
        products = productService.getProductsByTypeAndCategory(type, category, sorting);
        return products;
    }

    @GetMapping(value = "/products/query/{query}")
    public List<ProductDto> searchProduct(@PathVariable String query) {
        return productService.getProductsByQuery(query);
    }

    //reviews
    @GetMapping(value = "/reviews/{productId}")
    public List<ReviewDto> getReviews(@PathVariable Integer productId) {
        return productService.getReviews(productId);
    }

    @GetMapping(value = "/reviews/{productId}/{userId}")
    public Reviews getOneReview(@PathVariable Integer productId, @PathVariable Integer userId) {
        return productService.getOneReview(productId, userId);
    }




}
