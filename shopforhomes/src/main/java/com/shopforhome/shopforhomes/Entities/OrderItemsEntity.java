package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Order_Items")
public class OrderItemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String oiid;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private int quantity = 1;

    private double price;
}
