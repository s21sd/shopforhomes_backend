package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Wishlist")
public class WishlistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String wid;  

    private String uid;  

    private String pid;  

    private double totalPrice;  

    private String imagePaths;  

    private String productName;

    private String productDescription;  

    private String category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();  
}
