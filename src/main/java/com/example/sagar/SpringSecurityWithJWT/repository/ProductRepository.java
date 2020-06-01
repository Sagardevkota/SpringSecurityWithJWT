package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Products;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    @Query(value="SELECT * from products  limit ?1 offset ?2",nativeQuery=true)
    List<Products> getPaginatedProducts(int item_count,int to);
    List<Products> findAllByProductId(Integer productId);
    List<Products> findAllByCategory(String category);
    List<Products> findAllByBrand(String brand);
    List<Products> findAllByType(String type);
    @Query("SELECT p FROM products p WHERE p.productName like %:query% or p.category like %:query% or p.brand like %:query%")
    List<Products> searchForProducts(String query);
}
