package com.shopforhome.shopforhomes.DTO;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import lombok.Data;
import java.util.List;

@Data
public class ProductDiscountDTO {
    private String pid;
    private String name;
    private String description;
    private double originalPrice;
    private double discountedPrice;
    private double discountAmount;
    private String category;
    private List<String> imagePathsList;
    private int stock;
    private String appliedCouponCode;

    public ProductDiscountDTO(ProductsEntity product, DiscountCouponsEntity coupon) {
        this.pid = product.getPid();
        this.name = product.getName();
        this.description = product.getDescription();
        this.originalPrice = product.getPrice();
        this.category = product.getCategory();
        this.imagePathsList = product.getImagePathsList();
        this.stock = product.getStock();
        
        if (coupon != null) {
            this.discountAmount = coupon.getDiscount();
            this.discountedPrice = Math.max(0, originalPrice - discountAmount);
            this.appliedCouponCode = coupon.getCode();
        } else {
            this.discountedPrice = originalPrice;
            this.discountAmount = 0;
            this.appliedCouponCode = null;
        }
    }
}