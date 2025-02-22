package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart")
public class CartEntity {
    
    @Id
    @Column(length = 36)  // UUID format
    private String cid;

    private String productName;
    private int productPrice;
    private int quantity = 1;

    @Column(length = 36)
    private String userId;  // Foreign key reference to Users(uid)

    @Column(length = 36)
    private String productId;  // Foreign key reference to Products(pid)
}
