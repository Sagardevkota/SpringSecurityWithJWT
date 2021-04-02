package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.mapper.ProductMapper;
import com.example.sagar.SpringSecurityWithJWT.mapper.ReviewMapper;
import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.ReviewRepository;
import com.example.sagar.SpringSecurityWithJWT.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class ProductService {

    private String BASE_URL = "http://52.171.61.18:8080/";

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    private final AzureBlobService azureBlobService;

    @Autowired
    ProductService(AzureBlobService azureBlobService){
        this.azureBlobService = azureBlobService;
    }


    public List<ProductDto> getAllProducts(int page_number) {
        List<ProductDto> productDtoList;
        int item_count = 6;
        int to = (page_number - 1) * item_count;
        int totalItems = Math.toIntExact(productRepository.count());

        //if all item has been shown
        if (to > totalItems) return null;
        else {

            List<Products> products = productRepository.getPaginatedProducts(item_count, to);
            productDtoList = getProductResponse(products);


        }

        return productDtoList;
    }


    public List<Products> findAllByProductId(Integer id) {

        return productRepository.findAllByProductId(id);
    }

    public String getProductName(Integer productId) {
        return productRepository.getProductName(productId);
    }

    public List<ProductDto> getProductsByCategory(String category, String sorting) {

        List<Products> products;
        if (sorting.equalsIgnoreCase("Popularity")) {
            products = productRepository.findByCategoryOrderByProductName(category);

        } else if (sorting.equalsIgnoreCase("Price high to low")) {
            products = productRepository.findByCategoryOrderByMarked_priceDesc(category);
        } else {
            products = productRepository.findByCategoryOrderByMarked_priceAsc(category);

        }


        return getProductResponse(products);


    }

    public List<ProductDto> getProductsByBrands(String brand, String sorting) {
        List<Products> products;

        if (sorting.equalsIgnoreCase("Popularity")) {
            products = productRepository.findByBrandOrderByProductName(brand);
        } else if (sorting.equalsIgnoreCase("Price high to low")) {
            products = productRepository.findByBrandOrderByMarked_priceDesc(brand);
        } else {
            products = productRepository.findByBrandOrderByMarked_priceAsc(brand);
        }

        return getProductResponse(products);


    }

    public List<ProductDto> getProductsByType(String type, String sorting) {
        List<Products> products;


        if (sorting.equalsIgnoreCase("Popularity")) {
            products = productRepository.findByTypeOrderByProductName(type);
        } else if (sorting.equalsIgnoreCase("Price high to low")) {
            products = productRepository.findByTypeOrderByMarked_priceDesc(type);
        } else {
            products = productRepository.findByTypeOrderByMarked_priceAsc(type);
        }


        return getProductResponse(products);


    }

    public List<ProductDto> getProductsByQuery(String query) {
        List<Products> products = productRepository.searchForProducts(query);
        return getProductResponse(products);
    }

    private List<ProductDto> getProductResponse(List<Products> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        products.forEach(product -> {
            ProductDto productDto = productMapper.toDto(product); //get product dto without rating as ignore=true
            String rating = String.valueOf(productRepository.getRating(product.getProductId()));
            try {
                //convert string to list of string
                productMapper.setColors(product.getColors(),productDto);
                productMapper.setSizes(product.getSizes(),productDto);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }

            if (!rating .equals("null") ) {
                productMapper.setRating(rating, productDto); //set rating in product dto
            } else {
                productMapper.setRating("0", productDto);
            }
            productDtoList.add(productDto);
        });
    return productDtoList;
    }


    public void addProduct(Products products) {
        productRepository.save(products);
    }


    public String saveImage(MultipartFile file,String imageName) throws IOException {
        return azureBlobService.storeFile(imageName, file.getInputStream(), file.getSize());

    }


    public List<ProductDto> getProductsOfSeller(Integer sellerId) {
        List<Products> products = productRepository.getProductsOfSeller(sellerId);
        return getProductResponse(products);
    }

    public void updateProduct(ProductDto products) throws JsonProcessingException {
        String colors = productMapper.converJsonListToString(products.getColors());
        String sizes = productMapper.converJsonListToString(products.getSizes());
        productRepository.updateProduct(products.getProductName(), products.getDesc(), products.getPrice(), products.getPicturePath(), products.getCategory(), products.getBrand(), products.getSku(), products.getType(), products.getDiscount(), products.getStock(), products.getSeller_id(), products.getProductId(),colors,sizes);
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public List<ReviewDto> getReviews(Integer productId) {
        List<Reviews> reviewsList = reviewRepository.getReviews(productId);
        List<ReviewDto> reviewDtoList = new ArrayList<>();
        reviewsList.forEach(reviews -> {
            String userName = userRepository.getUserName(reviews.getUser_id());
            ReviewDto reviewDto = reviewMapper.toDto(reviews);
            reviewMapper.setUserName(userName,reviewDto);
            reviewDtoList.add(reviewDto);

        });

        return reviewDtoList;
    }

    public void addReviews(Reviews reviews) {
        reviewRepository.save(reviews);
    }

    public void updateReview(Reviews reviews) {
        reviewRepository.updateReview(reviews.getUser_id(), reviews.getProduct_id(), reviews.getMessage(), reviews.getRating(), reviews.getDate());
    }

    public Reviews getOneReview(Integer productId, Integer userId) {
        return reviewRepository.getOneReview(productId, userId);
    }

    public List<ProductDto> getProductsByTypeAndCategory(String type, String category, String sorting) {

        List<Products> products = new ArrayList<>();
        if (sorting.equalsIgnoreCase("Popularity")) {
            products = productRepository.findByTypeAndCategoryOrderByProductName(type, category);
        } else if (sorting.equalsIgnoreCase("Price high to low")) {
            products = productRepository.findByTypeAndCategoryOrderByMarked_priceDesc(type, category);
        } else {
            products = productRepository.findByTypeAndCategoryOrderByMarked_priceAsc(type, category);
        }


        return getProductResponse(products);
    }

    public Products getOneProduct(Integer productId) {
        return productRepository.getOneProduct(productId);
    }
}
