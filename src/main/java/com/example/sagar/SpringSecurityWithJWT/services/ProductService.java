package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

  public List<Products> getAllProducts(){

       return productRepository.findAll();
    }


    public List<Products> getOneProduct(Integer id){

        return productRepository.findAllByProductId(id);
    }

    public List<Products> getProductsByCategory(String category){

        return productRepository.findAllByCategory(category);
    }

    public List<Products> getProductsByBrands(String brand){

        return productRepository.findAllByBrand(brand);
    }
    public List<Products> getProductsByType(String type){

        return productRepository.findAllByType(type);
    }

    public List<Products> getProductsByQuery(String query){

        return productRepository.searchForProducts(query);
    }

}
