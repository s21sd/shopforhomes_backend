package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "OrderAddress")
public class OrderAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String oaid;

    @ManyToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "oid", referencedColumnName = "oid", nullable = false)
    private OrdersEntity order;

    @Column(name = "address", nullable = false)
    private String address;
}
