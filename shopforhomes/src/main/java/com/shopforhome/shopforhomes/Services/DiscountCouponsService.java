package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.DTO.DiscountCouponsDTO;
import com.shopforhome.shopforhomes.Dao.DiscountCouponsDao;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscountCouponsService {

    @Autowired
    private DiscountCouponsDao discountCouponsDao;

    @Autowired
    private UserDao userDao;

    // Convert Entity to DTO
    private DiscountCouponsDTO convertToDTO(DiscountCouponsEntity entity) {
        return new DiscountCouponsDTO(
            entity.getDiscountId(),
            entity.getCode(),
            entity.getDiscount(),
            entity.getExpiryDate(),
            entity.getIsApplied()
        );
    }

    // Get all discount coupons (returning DTO list)
    public ResponseEntity<List<DiscountCouponsDTO>> getAllCoupons() {
        List<DiscountCouponsDTO> coupons = discountCouponsDao.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    // Get a discount coupon by code
    public ResponseEntity<DiscountCouponsDTO> getCouponByCode(String code) {
        Optional<DiscountCouponsEntity> coupon = discountCouponsDao.findByCode(code);
        return coupon.map(value -> new ResponseEntity<>(convertToDTO(value), HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Add a new discount coupon
    public ResponseEntity<DiscountCouponsEntity> addCoupon(DiscountCouponsEntity coupon) {
        
        if (coupon.getUser() == null || coupon.getUser().getUid() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request if user is missing
        }
    
        Optional<UserEntity> user = userDao.findById(coupon.getUser().getUid());
        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 if user not found
        }

        if (!user.get().getRole().equals("Admin")) 
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden if not Admin
        }
    
        coupon.setUser(user.get());
    
        // Check if this user already has this coupon
        Optional<DiscountCouponsEntity> existingCoupon = discountCouponsDao.findByCodeAndUser(coupon.getCode(), user.get());
        if (existingCoupon.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict if user already has this coupon
        }
    
        // Save the new coupon for this user
        DiscountCouponsEntity savedCoupon = discountCouponsDao.save(coupon);
        return new ResponseEntity<>(savedCoupon, HttpStatus.CREATED);
    }
    

    // // Apply a discount by updating isApplied to true
    // public ResponseEntity<DiscountCouponsEntity> applyDiscount(String code) {
    //     Optional<DiscountCouponsEntity> coupon = discountCouponsDao.findByCode(code);
    //     if (coupon.isPresent()) {
    //         DiscountCouponsEntity discount = coupon.get();
    //         if (!discount.isApplied()) {
    //             discount.setApplied(true);
    //             discountCouponsDao.save(discount);
    //             return new ResponseEntity<>(discount, HttpStatus.OK);
    //         } else {
    //             return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 if already applied
    //         }
    //     }
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
}
