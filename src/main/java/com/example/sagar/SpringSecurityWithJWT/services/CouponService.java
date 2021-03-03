package com.example.sagar.SpringSecurityWithJWT.services;

import com.example.sagar.SpringSecurityWithJWT.model.Coupons;
import com.example.sagar.SpringSecurityWithJWT.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<Coupons> checkCoupon(Coupons coupons) {
        return couponRepository.findAllByCoupon_code(coupons.getCoupon_code(),
                coupons.getProduct_id());

    }

}
