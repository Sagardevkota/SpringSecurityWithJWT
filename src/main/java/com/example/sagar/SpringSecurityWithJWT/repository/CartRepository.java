package com.example.sagar.SpringSecurityWithJWT.repository;


import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Carts,Integer>
{
   @Query("select p,c.color,c.size,c.price from products p inner join carts c on p.productId=c.product_id inner join user_info u on c.user_id=u.id where c.user_id=?1")
    List<Products> getCartList(Integer userId);

   @Query(value="SELECT * FROM carts WHERE product_id =?1 AND user_id=?2",nativeQuery=true)
   List<Carts> findAllByProduct_idAndUser_id(Integer product_id,Integer user_id);



   @Query(value="SELECT product_color from carts where product_id=?1 AND user_id=?2",nativeQuery=true)
   String getcolor(Integer product_id, Integer userId);

   @Query(value="SELECT product_size from carts where  product_id=?1 AND user_id=?2",nativeQuery=true)
   Float getSize(Integer product_id, Integer userId);


   @Transactional
   @Modifying
   @Query(value="DELETE  FROM carts WHERE product_id=?1 AND user_id=?2",nativeQuery=true)
    void deleteFromCart(Integer product_id,Integer user_id);


   @Query(value="SELECT price from carts where  product_id=?1 AND  user_id=?2",nativeQuery=true)
   String getPrice(int productId,Integer user_id);
}
