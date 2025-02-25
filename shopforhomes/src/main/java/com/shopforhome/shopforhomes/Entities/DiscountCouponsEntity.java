package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DiscountCoupons")
public class DiscountCouponsEntity {

    @Id
    @Column(length = 36)
    private String discountId;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private double discount;

    @ManyToOne
    @JoinColumn(name = "uid")
    // @Column(length = 36)
    private UserEntity userId;  

    @Column(nullable = false)
    private java.sql.Date expiryDate;

    @Column(nullable = false)
    private boolean isApplied = false;  
}
