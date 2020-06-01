package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.Products;
import com.example.sagar.SpringSecurityWithJWT.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

  public List<Products> getAllProducts(int page_number){

      int item_count=6;
      int to=(page_number-1)*item_count;
     int totalItems=Math.toIntExact(productRepository.count());

     if (to>totalItems) return null;
     else
       return productRepository.getPaginatedProducts(item_count,to);
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
