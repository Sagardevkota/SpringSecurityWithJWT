package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>
{
    @Query(value="SELECT * from products  limit ?1 offset ?2",nativeQuery=true)
    List<Products> getPaginatedProducts(int item_count,int to);

    List<Products> findAllByProductId(Integer productId);

    //category

    @Query(value="SELECT * From products where category=?1 ORDER By marked_price DESC ",nativeQuery=true)
    List<Products> findByCategoryOrderByMarked_priceDesc(String category);

    @Query(value="SELECT * From products where category=?1 ORDER By marked_price ASC ",nativeQuery=true)
    List<Products> findByCategoryOrderByMarked_priceAsc(String category);

    @Query(value="SELECT * From products where category=?1 ORDER By product_name ",nativeQuery=true)
    List<Products> findByCategoryOrderByProductName(String category);

    //brand
    @Query(value="SELECT * From products where brand=?1 ORDER By marked_price DESC ",nativeQuery=true)
    List<Products> findByBrandOrderByMarked_priceDesc(String brand);

    @Query(value="SELECT * From products where brand=?1 ORDER By marked_price ASC ",nativeQuery=true)
    List<Products> findByBrandOrderByMarked_priceAsc(String brand);

    @Query(value="SELECT * From products where brand=?1 ORDER By product_name ",nativeQuery=true)
    List<Products> findByBrandOrderByProductName(String brand);

    //type
    @Query(value="SELECT * From products where type=?1 ORDER By marked_price DESC ",nativeQuery=true)
    List<Products> findByTypeOrderByMarked_priceDesc(String type);

    @Query(value="SELECT * From products where type=?1 ORDER By marked_price ASC ",nativeQuery=true)
    List<Products> findByTypeOrderByMarked_priceAsc(String type);

    @Query(value="SELECT * From products where type=?1 ORDER By product_name",nativeQuery=true)
    List<Products> findByTypeOrderByProductName(String type);

    @Query("SELECT p FROM products p WHERE p.productName like %:query% or p.category like %:query% or p.brand like %:query%")
    List<Products> searchForProducts(String query);

}
