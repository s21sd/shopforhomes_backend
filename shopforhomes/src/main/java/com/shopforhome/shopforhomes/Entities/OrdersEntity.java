package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
// import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Orders")
public class OrdersEntity {

    @Id
    @Column(length = 36)  // UUID format
    private String oid;

    @Column(length = 36)
    private String userId; // Foreign key reference to Users(uid)

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); 
}
