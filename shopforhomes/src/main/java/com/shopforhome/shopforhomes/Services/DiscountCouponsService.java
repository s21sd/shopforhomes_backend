package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.DiscountCouponsDao;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountCouponsService {

    @Autowired
    private DiscountCouponsDao discountCouponsDao;

    // Get all discount coupons
    public ResponseEntity<List<DiscountCouponsEntity>> getAllCoupons() {
        List<DiscountCouponsEntity> coupons = discountCouponsDao.findAll();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Get a discount coupon by code
    public ResponseEntity<DiscountCouponsEntity> getCouponByCode(String code) {
        Optional<DiscountCouponsEntity> coupon = discountCouponsDao.findByCode(code);
        return coupon.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add a new discount coupon
    public ResponseEntity<DiscountCouponsEntity> addCoupon(DiscountCouponsEntity coupon) {
        DiscountCouponsEntity savedCoupon = discountCouponsDao.save(coupon);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }


    // Update isApplied status
    public ResponseEntity<DiscountCouponsEntity> applyDiscount(String code) {
        Optional<DiscountCouponsEntity> coupon = discountCouponsDao.findByCode(code);
        if (coupon.isPresent()) {
            DiscountCouponsEntity existingCoupon = coupon.get();
            existingCoupon.setApplied(true);
            discountCouponsDao.save(existingCoupon);
            return new ResponseEntity<>(existingCoupon, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
