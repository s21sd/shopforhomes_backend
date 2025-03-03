package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "CartEntity")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cid;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "pid", referencedColumnName = "pid", nullable = false)
    private ProductsEntity product;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 1")
    private int quantity;
}