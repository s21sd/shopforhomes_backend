package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Services.DiscountCouponsService;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class DiscountCouponsController {

    @Autowired
    private DiscountCouponsService discountCouponsService;

    // Get all discount coupons
    @GetMapping
    public ResponseEntity<List<DiscountCouponsEntity>> getAllCoupons() {
        return discountCouponsService.getAllCoupons();
    }

    // Get a discount coupon by code
    @GetMapping("/{code}")
    public ResponseEntity<DiscountCouponsEntity> getCouponByCode(@PathVariable String code) {
        return discountCouponsService.getCouponByCode(code);
    }

    // Add a new discount coupon
    @PostMapping("/addDiscount")
    public ResponseEntity<DiscountCouponsEntity> addCoupon(@RequestBody DiscountCouponsEntity coupon) {
        return discountCouponsService.addCoupon(coupon);
    }

    // // Apply a discount by updating isApplied to true
    // @PutMapping("/apply/{code}")
    // public ResponseEntity<DiscountCouponsEntity> applyDiscount(@PathVariable String code) {
    //     return discountCouponsService.applyDiscount(code);
    // }
}
