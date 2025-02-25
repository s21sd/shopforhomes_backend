package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Review")
public class ReviewEntity 
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String rid;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity userId;

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductsEntity productId;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
