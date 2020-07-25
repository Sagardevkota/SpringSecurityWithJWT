package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer>


{
    @Query(value="SELECT * from products  limit ?1 offset ?2",nativeQuery=true)
    List<Products> getPaginatedProducts(int item_count,int to);

    List<Products>  findAllByProductId(Integer productId);

   @Query(value="SELECT * FROM products where product_id=?1",nativeQuery=true)
   Products getOneProduct(Integer product_id);

    //category

    @Query(value="SELECT * From products where category=?1 ORDER By price DESC ",nativeQuery=true)
    List<Products> findByCategoryOrderByMarked_priceDesc(String category);

    @Query(value="SELECT * From products where category=?1 ORDER By price ASC ",nativeQuery=true)
    List<Products> findByCategoryOrderByMarked_priceAsc(String category);

    @Query(value="SELECT * From products where category=?1 ORDER By product_name ",nativeQuery=true)
    List<Products> findByCategoryOrderByProductName(String category);

    //brand
    @Query(value="SELECT * From products where brand=?1 ORDER By price DESC ",nativeQuery=true)
    List<Products> findByBrandOrderByMarked_priceDesc(String brand);

    @Query(value="SELECT * From products where brand=?1 ORDER By price ASC ",nativeQuery=true)
    List<Products> findByBrandOrderByMarked_priceAsc(String brand);

    @Query(value="SELECT * From products where brand=?1 ORDER By product_name ",nativeQuery=true)
    List<Products> findByBrandOrderByProductName(String brand);

    //type
    @Query(value="SELECT * From products where type=?1 ORDER By price DESC ",nativeQuery=true)
    List<Products> findByTypeOrderByMarked_priceDesc(String type);

    @Query(value="SELECT * From products where type=?1 ORDER By price ASC ",nativeQuery=true)
    List<Products> findByTypeOrderByMarked_priceAsc(String type);

    @Query(value="SELECT * From products where type=?1 ORDER By product_name",nativeQuery=true)
    List<Products> findByTypeOrderByProductName(String type);

    @Query("SELECT p FROM products p WHERE p.productName like %:query% or p.category like %:query% or p.brand like %:query%")
    List<Products> searchForProducts(String query);

    @Query(value="select product_id from products where product_name=?1",nativeQuery=true)
    Integer getId(String productName);

    @Query(value="SELECT * from products where seller_id=?1",nativeQuery=true)
    List<Products> getProductsOfSeller(Integer sellerId);

    //update
    @Transactional
    @Modifying
    @Query(value="update products p SET p.product_name=?1,p.description=?2,p.price=?3,p.picture_path=?4,p.category=?5,p.brand=?6,p.sku=?7,p.type=?8,p.discount=?9,p.stock=?10,p.seller_id=?11 where p.product_id=?12",nativeQuery=true)
    void updateProduct(String productName,String desc,String price,String picturePath,String category,String brand,String sku,String type,Integer discount,Integer stock,Integer sellerId,Integer productId);


 @Transactional
 @Modifying
 @Query(value="update products p SET p.stock=?1 where product_id=?2",nativeQuery=true)
 void changeStock(Integer qty, Integer productId);

 @Query(value="Select stock from products where product_id=?1",nativeQuery=true)
 Integer getStock(Integer productId);


 //getrating
 @Query(value = "select avg(r.rating) as rating from products p  join reviews r on p.product_id=r.product_id WHERE p.product_id=?1",nativeQuery=true)
 Float getRating(Integer productId);

 @Query(value = "SELECT product_id from products where seller_id=?1",nativeQuery=true)
    List<Integer> getProductIds(Integer sellerId);




//for type and category
 @Query(value = "SELECT * FROM `products` WHERE type=?1 AND category=?2 ORDER BY price ASC",nativeQuery = true)
 List<Products> findByTypeAndCategoryOrderByMarked_priceAsc(String type, String category);

 @Query(value = "SELECT * FROM `products` WHERE type=?1 AND category=?2 ORDER BY price DESC",nativeQuery = true)
 List<Products> findByTypeAndCategoryOrderByMarked_priceDesc(String type, String category);

 @Query(value = "SELECT * FROM `products` WHERE type=?1 AND category=?2 ORDER BY product_name",nativeQuery = true)
 List<Products> findByTypeAndCategoryOrderByProductName(String type, String category);

 @Query(value = "select product_name from products where product_id=?1",nativeQuery = true)
    String getProductName(Integer productId);
}
