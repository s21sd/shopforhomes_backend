package com.shopforhome.shopforhomes.DTO;

import com.shopforhome.shopforhomes.Entities.OrderStatus;
import java.time.LocalDateTime;
import lombok.*;

@Data
public class OrderDTO {
    private String oid;
    private String uid;  // Only expose user ID, not full UserEntity
    private double totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderDTO(String oid, String uid, double totalPrice, OrderStatus status, LocalDateTime createdAt) {
        this.oid = oid;
        this.uid = uid;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getOid() {
        return oid;
    }

    public String getUid() {
        return uid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
