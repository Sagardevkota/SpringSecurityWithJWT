package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Carts,Integer> {
   @Query("select p from products p inner join carts c on p.productId=c.product_id inner join user_info u on c.user_id=u.id where c.user_id=?1")
    List<Products> getCartList(Integer userId);



}
