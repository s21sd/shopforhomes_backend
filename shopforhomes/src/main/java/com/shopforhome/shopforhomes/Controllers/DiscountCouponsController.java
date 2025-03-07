package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopforhome.shopforhomes.DTO.DiscountCouponsDTO;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Services.DiscountCouponsService;

@RestController
@RequestMapping("/api/coupons")
public class DiscountCouponsController {

    @Autowired
    private DiscountCouponsService discountCouponsService;

    // Get a discount coupon by code
    @GetMapping("/{code}")
    public ResponseEntity<DiscountCouponsDTO> getCouponByCode(@PathVariable String code) {
        return discountCouponsService.getCouponByCode(code);
    }

    // Add a new discount coupon
    @PostMapping("/addDiscount")
    public ResponseEntity<String> addCoupon(@RequestBody DiscountCouponsEntity coupon, @RequestParam String uid) {
        return discountCouponsService.addCoupon(coupon);
    }



    @DeleteMapping("/delete/{discountId}")
    public ResponseEntity<String> removeDiscountcoupons(@PathVariable String discountId) {
        return discountCouponsService.removeDiscountcoupons(discountId);
    }
}
