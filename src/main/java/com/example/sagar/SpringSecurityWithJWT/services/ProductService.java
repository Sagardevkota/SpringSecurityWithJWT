package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private String BASE_URL = "http://52.171.61.18:8080/";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ProductResponse> getAllProducts(int page_number) {
        List<ProductResponse> productResponses=new ArrayList<>();
        int item_count = 6;
        int to = (page_number - 1) * item_count;
        int totalItems = Math.toIntExact(productRepository.count());

        if (to > totalItems) return null;
        else
        {

            List<Products> products=productRepository.getPaginatedProducts(item_count, to);
          productResponses= getProductResponse(products);
        }

            return productResponses;
    }


    public List<Products> getOneProduct(Integer id) {

        return productRepository.findAllByProductId(id);
    }

    public String getProductName(Integer productId)
    {
        return  productRepository.getProductName(productId);
    }

    public List<ProductResponse> getProductsByCategory(String category, String sorting) {

        List<Products> products=new ArrayList<>();
        if (sorting.equalsIgnoreCase("Popularity"))
        {
            products=productRepository.findByCategoryOrderByProductName(category);

        }

       else if (sorting.equalsIgnoreCase("Price high to low"))
        {
            products=productRepository.findByCategoryOrderByMarked_priceDesc(category);
        }

        else
        {
            products=productRepository.findByCategoryOrderByMarked_priceAsc(category);

        }


        return getProductResponse(products);


    }

    public List<ProductResponse> getProductsByBrands(String brand, String sorting) {
        List<Products> products=new ArrayList<>();

        if (sorting.equalsIgnoreCase("Popularity"))
        {
            products= productRepository.findByBrandOrderByProductName(brand);
        }

      else if (sorting.equalsIgnoreCase("Price high to low"))
        {
            products= productRepository.findByBrandOrderByMarked_priceDesc(brand);
        }

        else
        {
            products= productRepository.findByBrandOrderByMarked_priceAsc(brand);
        }


        return getProductResponse(products);


    }

    public List<ProductResponse> getProductsByType(String type, String sorting) {
        List<Products> products=new ArrayList<>();


        if (sorting.equalsIgnoreCase("Popularity"))
        {
            products= productRepository.findByTypeOrderByProductName(type);
        }

       else if (sorting.equalsIgnoreCase("Price high to low"))
        {
            products= productRepository.findByTypeOrderByMarked_priceDesc(type);
        }

        else
        {
            products= productRepository.findByTypeOrderByMarked_priceAsc(type);
        }


        return getProductResponse(products);


    }

    public List<ProductResponse> getProductsByQuery(String query) {
        List<Products> products=productRepository.searchForProducts(query);
        return getProductResponse(products);
    }

    private List<ProductResponse> getProductResponse(List<Products> products) {
        List<ProductResponse> productResponses=new ArrayList<>();
        for (Products p:products)
        {
            String myrating="0";
            String rating=String.valueOf(productRepository.getRating(p.getProductId()));
            if (!rating.equalsIgnoreCase("null"))
                myrating=rating;
            productResponses.add(new ProductResponse(
                    p.getProductId(),
                    p.getProductName(),
                    p.getDesc(),
                    p.getPrice(),
                    p.getCategory(),
                    p.getBrand(),
                    p.getSku(),p.getType(),
                    p.getPicture_path(),
                    p.getDiscount(),
                    p.getStock(),
                    p.getSeller_id(),
                    myrating
            ));

        }

        return productResponses;
    }


    public void addProduct(Products products) {
        productRepository.save(products);
    }

    public void addColor(ColorAttribute colorAttribute) {

        colorRepository.save(colorAttribute);

    }

//    private boolean checkifColorExists(ColorAttribute colorAttribute) {
//        int count=colorRepository.countColor(colorAttribute.getProduct_id(),colorAttribute.getColor());
//        if (count>0)
//            return true;
//        else
//            return false;
//
//    }

    public void clearColors(Integer productId){
        colorRepository.clearColors(productId);

    }

    public void clearSizes(Integer productId){
        sizeRepository.clearSizes(productId);

    }

    public void addSize(SizeAttribute sizeAttribute) {
        sizeRepository.save(sizeAttribute);

    }

//    private boolean checkifSizeExists(SizeAttribute sizeAttribute) {
//        int count=sizeRepository.countSize(sizeAttribute.getProduct_id(),sizeAttribute.getSize());
//        if (count>0)
//            return true;
//        else
//            return false;
//
//    }

    public Integer getProductId(String productName) {
        return productRepository.getId(productName);
    }

    public JsonResponse saveImage(MultipartFile file) {
        Logger logger = LoggerFactory.getLogger(ProductService.class);


        String folder = "/api/photos/";
        try {
            byte[] bytes =file.getBytes();
            Path path = Paths.get(folder +file.getOriginalFilename());
            String location=BASE_URL+file.getOriginalFilename();
            Files.write(path, bytes);
            logger.info("Saved image to "+location);
            return new JsonResponse("200 OK", location);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResponse("500 Internal server error", e.getMessage());
        }


    }


    public List<Products> getProductsOfSeller(Integer sellerId) {
        return productRepository.getProductsOfSeller(sellerId);
    }

    public void updateProduct(Products products) {
        productRepository.updateProduct(products.getProductName(),products.getDesc(),products.getPrice(),products.getPicture_path(),products.getCategory(),products.getBrand(),products.getSku(),products.getType(),products.getDiscount(),products.getStock(),products.getSeller_id(),products.getProductId());
    }

    public void deleteProduct(Integer productId) {
        productRepository.deleteById(productId);
    }

    public List<ReviewResponse> getReviews(Integer productId) {
       List<Reviews> reviews= reviewRepository.getReviews(productId);
       List<ReviewResponse> reviewResponses=new ArrayList<>();
       for (Reviews reviews1:reviews){
           String user_name=userRepository.getUserName(reviews1.getUser_id());
           reviewResponses.add(new ReviewResponse(
                   reviews1.getId(),
                   user_name,
                   reviews1.getProduct_id(),
                   reviews1.getMessage(),
                   reviews1.getRating(),
                   reviews1.getDate()

           ));
       }
       return reviewResponses;
    }


    public void addReviews(Reviews reviews) {
        reviewRepository.save(reviews);
    }

    public void updateReview(Reviews reviews) {
        reviewRepository.updateReview(reviews.getUser_id(),reviews.getProduct_id(),reviews.getMessage(),reviews.getRating(),reviews.getDate());
    }

    public Reviews getOneReview(Integer productId, Integer userId) {
        return reviewRepository.getOneReview(productId,userId);
    }

    public List<ProductResponse> getProductsByTypeAndCategory(String type, String category, String sorting) {

        List<Products> products=new ArrayList<>();
        if (sorting.equalsIgnoreCase("Popularity"))
        {
            products= productRepository.findByTypeAndCategoryOrderByProductName(type,category);
        }

        else if (sorting.equalsIgnoreCase("Price high to low"))
        {
            products= productRepository.findByTypeAndCategoryOrderByMarked_priceDesc(type,category);
        }

        else
        {
            products= productRepository.findByTypeAndCategoryOrderByMarked_priceAsc(type,category);
        }


        return getProductResponse(products);
    }
}
