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

    public List<Products> getAllProducts(int page_number) {

        int item_count = 6;
        int to = (page_number - 1) * item_count;
        int totalItems = Math.toIntExact(productRepository.count());

        if (to > totalItems) return null;
        else
            return productRepository.getPaginatedProducts(item_count, to);
    }


    public List<Products> getOneProduct(Integer id) {

        return productRepository.findAllByProductId(id);
    }

    public List<Products> getProductsByCategory(String category,String sorting) {

        if (sorting.equalsIgnoreCase("Popularity"))
            return productRepository.findByCategoryOrderByProductName(category);
        if (sorting.equalsIgnoreCase("Price high to low"))
            return productRepository.findByCategoryOrderByMarked_priceDesc(category);
        else
            return productRepository.findByCategoryOrderByMarked_priceAsc(category);

    }

    public List<Products> getProductsByBrands(String brand,String sorting) {

        if (sorting.equalsIgnoreCase("Popularity"))
            return productRepository.findByBrandOrderByProductName(brand);
        if (sorting.equalsIgnoreCase("Price high to low"))
            return productRepository.findByBrandOrderByMarked_priceDesc(brand);
        else
            return productRepository.findByBrandOrderByMarked_priceAsc(brand);

    }

    public List<Products> getProductsByType(String type,String sorting) {

        if (sorting.equalsIgnoreCase("Popularity"))
            return productRepository.findByTypeOrderByProductName(type);
        if (sorting.equalsIgnoreCase("Price high to low"))
            return productRepository.findByTypeOrderByMarked_priceDesc(type);
        else
            return productRepository.findByTypeOrderByMarked_priceAsc(type);

    }

    public List<Products> getProductsByQuery(String query) {

        return productRepository.searchForProducts(query);
    }


}
