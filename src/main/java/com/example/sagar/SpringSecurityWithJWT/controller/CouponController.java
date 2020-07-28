package com.example.sagar.SpringSecurityWithJWT.controller;

import com.example.sagar.SpringSecurityWithJWT.model.Carts;
import com.example.sagar.SpringSecurityWithJWT.model.Coupons;
import com.example.sagar.SpringSecurityWithJWT.model.JsonResponse;
import com.example.sagar.SpringSecurityWithJWT.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/coupon" ,method = RequestMethod.POST)
    public JsonResponse checkCoupon(@RequestBody Coupons coupons )
    {
        List<Coupons> coupons1=couponService.checkCoupon(coupons);
        if (coupons1.size()>0)
           return new JsonResponse("200 OK",String.valueOf(coupons1.get(0).getDiscount()));
       else return new JsonResponse("409 Conflict","Invalid coupon code");

    }

}
