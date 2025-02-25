package com.shopforhome.shopforhomes.Entities;

import java.util.*;
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

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity userId; // Foreign key reference to Users(uid)

    private double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); 

    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<OrderItemsEntity> orderItems;

}
