package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DiscountCouponsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String discountId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private double discount;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private java.sql.Date expiryDate;

    @Column(nullable = false)
    private int isApplied = 0;  // 0 = Not Applied, 1 = Applied
}
