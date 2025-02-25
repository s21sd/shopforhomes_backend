package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Entity
@Table(name = "Cart")
public class CartEntity {
    
    @Id
    @Column(length = 36)  // UUID format
    private String cid;

    private String productName;

    private double productPrice;

    @Column(nullable = false)
    private int quantity = 1;

    @ManyToOne
    @JoinColumn(name = "uid", nullable = false)
    private UserEntity  user;  // Foreign key reference to Users(uid)

    @ManyToOne
    @JoinColumn(name = "pid", nullable = false)
    private ProductsEntity product;  // Foreign key reference to Products(pid)


    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setProduct(ProductsEntity product) {
        this.product = product;
    }
}
