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

    @ManyToOne
    @JoinColumn(name = "oid", nullable = false)
    private OrdersEntity orderId;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductsEntity productId;

    @Column(nullable = false)
    private int quantity = 1;

    private double price;
}
