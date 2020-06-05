package com.example.sagar.SpringSecurityWithJWT.repository;

import com.example.sagar.SpringSecurityWithJWT.model.Coupons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupons,Integer> {

    @Query(value="SELECT * FROM coupons where coupon_code=?1 AND product_id=?2",nativeQuery=true)
    List<Coupons> findAllByCoupon_code(String coupon_code,Integer product_id);
}
