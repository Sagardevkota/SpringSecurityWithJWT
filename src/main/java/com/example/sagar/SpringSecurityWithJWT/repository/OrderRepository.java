package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Order;
import com.example.sagar.SpringSecurityWithJWT.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

//    @Query(value="SELECT p.product_name, p.product_id,p.discount,p.picture_path,o.order_id,o.product_color,o.product_size,o.ordered_date,o.delivered_date,o.delivery_address,o.quantity,o.price,o.status FROM products p INNER JOIN orders o on p.product_id=o.product_id INNER JOIN user_info u on u.user_id=o.user_id WHERE o.user_id=?1",nativeQuery=true)
//    List<OrderResponse> getOrdersResponse(Integer userId);

     // for customers
    @Query(value="SELECT * FROM orders where user_id=?1 AND status=?2",nativeQuery=true)
    List<Order> getOrders(Integer userId,String status);







      //for sellers
    @Query(value="SELECT o.* FROM orders o INNER JOIN products p on p.product_id=o.product_id WHERE p.seller_id=?1 AND status=?2",nativeQuery=true)
    List<Order> getOrdersForSeller(Integer seller_id,String status);


    //update orderedDate
    @Modifying
    @Transactional
    @Query(value="UPDATE orders SET delivered_date=?2 WHERE order_id=?1",nativeQuery=true)
    void changeOrderedDate(Integer orderId, String deliveredDate);

    //update status
    @Modifying
    @Transactional
    @Query(value="UPDATE orders SET status=?2 WHERE order_id=?1",nativeQuery=true)
    void changeStatus(Integer orderId, String status);
}
