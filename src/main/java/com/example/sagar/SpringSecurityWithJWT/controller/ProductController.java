package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getProducts/{pageNumber}" ,method = RequestMethod.GET)
    public List<Products> getAllProducts(@PathVariable Integer pageNumber)
    {
        return productService.getAllProducts(pageNumber);

    }

    @RequestMapping(value = "/getProducts/id/{id}",method = RequestMethod.GET)
    public List<Products> getOneProducts(@PathVariable Integer id)
    {

        return productService.getOneProduct(id);
    }

    @RequestMapping(value = "/getProducts/{query}/{queryValue}/{sorting}",method = RequestMethod.GET)
    public List<Products> getOneProducts(@PathVariable String query,@PathVariable String queryValue,@PathVariable String sorting)
    {
        List<Products> products=new ArrayList<>();
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

    @RequestMapping(value = "/getProducts/query/{query}",method = RequestMethod.GET)
    public List<Products> getOneProducts(@PathVariable String query)
    {
        return  productService.getProductsByQuery(query);
    }

}
