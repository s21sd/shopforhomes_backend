package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DiscountCoupons", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"code", "uid"}) // Ensures the same user cannot have the same coupon twice
})
public class DiscountCouponsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String discountId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private double discount;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private java.sql.Date expiryDate;

    @Column(nullable = false)
    private boolean isApplied = false;  // Default value is false
}
