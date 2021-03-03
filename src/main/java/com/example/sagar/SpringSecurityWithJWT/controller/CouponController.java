package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Coupons;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class CouponController {

    private final CouponService couponService;

    @Autowired
    CouponController(CouponService couponService){
        this.couponService = couponService;
    }

    @PostMapping(value = "/coupon" )
    public JsonResponse checkCoupon(@RequestBody Coupons coupons )
    {
        List<Coupons> coupons1=couponService.checkCoupon(coupons);
        if (coupons1.size()>0)
           return new JsonResponse("200 OK",String.valueOf(coupons1.get(0).getDiscount()));
       else return new JsonResponse("409 Conflict","Invalid coupon code");

    }

}
