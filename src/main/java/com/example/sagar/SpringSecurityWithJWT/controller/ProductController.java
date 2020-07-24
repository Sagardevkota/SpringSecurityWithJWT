package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.*;
import com.example.sagar.SpringSecurityWithJWT.services.ColorAttrService;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import com.example.sagar.SpringSecurityWithJWT.services.SizeAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ColorAttrService colorAttrService;

    @Autowired
    private SizeAttrService sizeAttrService;


    @RequestMapping(value = "/product" , method = RequestMethod.POST)
    public JsonResponse addProduct(@RequestBody Products products){

        try {
            productService.addProduct(products);


         Integer product_id=productService.getProductId(products.getProductName());

            return new JsonResponse("200 Ok",String.valueOf(product_id));

        }
        catch (Exception e){
            return new JsonResponse("500 Internal error",e.getMessage());
        }


    }

    @RequestMapping(value = "/product" ,method = RequestMethod.PUT)
    public JsonResponse updateProduct(@RequestBody Products products)
    {
        try {
            productService.updateProduct(products);
            return new JsonResponse("200 OK","Updated product");
        }
        catch (Exception e){
            return new JsonResponse("500 Internal Server error",e.getMessage());
        }


    }

    @RequestMapping(value = "/product/{pageNumber}" ,method = RequestMethod.GET)
    public List<ProductResponse> getAllProducts(@PathVariable Integer pageNumber)
    {
        return productService.getAllProducts(pageNumber);

    }

    @RequestMapping(value = "/product/id/{id}",method = RequestMethod.GET)
    public List<Products> getOneProducts(@PathVariable Integer id)
    {

        return productService.getOneProduct(id);
    }

    @RequestMapping(value = "/product/{query}/{queryValue}/{sorting}",method = RequestMethod.GET)
    public List<ProductResponse> getProducts(@PathVariable String query,@PathVariable String queryValue,@PathVariable String sorting)
    {
        List<ProductResponse> products=new ArrayList<>();
        switch (query)
        {
            case "category":
                products= productService.getProductsByCategory(queryValue,sorting);
                break;
            case "brand":
                products=productService.getProductsByBrands(queryValue,sorting);
                break;
            case "type":
                products=productService.getProductsByType(queryValue,sorting);
                break;
        }
        return products;
    }

    @RequestMapping(value = "/product/type/{type}/category/{category}/{sorting}",method = RequestMethod.GET)
    public List<ProductResponse> getProductsByTypeAndCategory(@PathVariable String type,@PathVariable String category,@PathVariable String sorting)
    {
        List<ProductResponse> products=new ArrayList<>();
       products=productService.getProductsByTypeAndCategory(type,category,sorting);
        return products;
    }

    @RequestMapping(value = "/product/query/{query}",method = RequestMethod.GET)
    public List<ProductResponse> getOneProducts(@PathVariable String query)
    {
        return  productService.getProductsByQuery(query);
    }

    @RequestMapping(value = "/color/{productId}",method = RequestMethod.GET)
    public List<ColorAttribute> getColors(@PathVariable Integer productId)
    {
        return  colorAttrService.getColors(productId);
    }

    @RequestMapping(value = "/size/{productId}",method = RequestMethod.GET)
    public List<SizeAttribute> getSizes(@PathVariable Integer productId)
    {
        return  sizeAttrService.getSizes(productId);
    }

    @RequestMapping(value = "/color",method = RequestMethod.POST)
    public JsonResponse addColor(@RequestBody ColorAttribute colorAttribute)
    {
        try {
            String color=colorAttribute.getColor();
            if (!color.isEmpty()){

                productService.clearColors(colorAttribute.getProduct_id());
                List<String> colorList = Arrays.asList(color.split(","));
                for (String color1:colorList) {
                    ColorAttribute colorAttribute1=new ColorAttribute(colorAttribute.getProduct_id(),color1.trim());
                    productService.addColor(colorAttribute1);
                }
            }

            return new JsonResponse("200 OK","Color added");
        }

        catch (Exception e){
            return new JsonResponse("500 Internal Server error",e.getMessage());
        }

    }

    @RequestMapping(value = "/size",method = RequestMethod.POST)
    public JsonResponse addColor(@RequestBody SizeAttribute sizeAttribute)
    {
        try {

            String size=String.valueOf(sizeAttribute.getSize());
            if (!size.isEmpty()){
                productService.clearSizes(sizeAttribute.getProduct_id());
                List<String> sizeList = Arrays.asList(size.split(","));
                for (String size1:sizeList) {
                    SizeAttribute sizeAttribute1=new SizeAttribute(sizeAttribute.getProduct_id(),size1);
                    productService.addSize(sizeAttribute1);
                }
            }

            return new JsonResponse("200 OK","Size added");
        }

        catch (Exception e){
            return new JsonResponse("500 Internal Server error",e.getMessage());
        }

    }



    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public JsonResponse uploadImage(@RequestParam("image")  MultipartFile image) {

       return productService.saveImage(image);

    }

    @RequestMapping(value = "/product/sellerId/{sellerId}",method = RequestMethod.GET)
    public List<Products> getProductsOFSeller(@PathVariable Integer sellerId)
    {

        return productService.getProductsOfSeller(sellerId);
    }

    @RequestMapping(value = "/product/{productId}",method = RequestMethod.DELETE)
    public JsonResponse deleteProduct(@PathVariable Integer productId)
    {
        //first clear all related info or else foreign key constraint fails will occur
        productService.clearSizes(productId);
        productService.clearColors(productId);

        //now delete the product
        productService.deleteProduct(productId);

        return new JsonResponse("200 OK","Deleted the item");
    }


    //reviews
    @RequestMapping(value = "/reviews/{productId}",method = RequestMethod.GET)
    public List<ReviewResponse> getReviews(@PathVariable Integer productId)
    {
        return productService.getReviews(productId);
    }

    @RequestMapping(value = "/reviews/{productId}/{userId}",method = RequestMethod.GET)
    public Reviews getOneReview(@PathVariable Integer productId, @PathVariable Integer userId)
    {
        return productService.getOneReview(productId,userId);
    }

    @RequestMapping(value = "/reviews",method = RequestMethod.POST)
    public JsonResponse addReview(@RequestBody Reviews reviews)
    {
         productService.addReviews(reviews);
         return new JsonResponse("200 OK","Added review");
    }

    @RequestMapping(value = "/reviews",method = RequestMethod.PUT)
    public JsonResponse updateReview(@RequestBody Reviews reviews)
    {
        productService.updateReview(reviews);
        return new JsonResponse("200 OK","Updated review");
    }




}
