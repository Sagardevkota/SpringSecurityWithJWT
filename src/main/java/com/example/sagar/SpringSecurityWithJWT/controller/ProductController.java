package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.configuration.UserPrincipal;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
public class ProductController {

    private final ProductService productService;

    //keep track of products that have been shown to avoid duplicate
    private final List<Integer> productIdsList = new ArrayList<>();

    @Autowired
    ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping(value = "/products/{pageNumber}")
    public List<ProductDto> getAllProducts(@PathVariable int pageNumber, @CurrentSecurityContext Authentication authentication) {
        if (pageNumber==1)productIdsList.clear();


        List<ProductDto> list;
        list = getRecommendedProducts(getUserId(authentication));
        //if we have no recommendation just return what is in db my adding from
        List<ProductDto> listFromDb = productService.getAllProducts(pageNumber);
        list.addAll(listFromDb);

        List<ProductDto> listToRemove = new ArrayList<>();

        //check if product id is already in list
        list.forEach(productDto -> {
            if (productIdsList.contains(productDto.getProductId()))
                listToRemove.add(productDto);
            else productIdsList.add(productDto.getProductId());
        });

        list.removeAll(listToRemove);


        return list;

    }

    private int getUserId(Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal(); //cast principal object to our user principal
        return user.getId();
    }


    private List<ProductDto> getRecommendedProducts(int userId) {
        return productService.getRecommendedProducts(userId);
    }


    @GetMapping(value = "/products/hot-deals/{pageNumber}")
    public List<ProductDto> getHotDeals(@PathVariable Integer pageNumber, @CurrentSecurityContext Authentication authentication) {
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
