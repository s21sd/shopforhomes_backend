package com.shopforhome.shopforhomes.Dao;

import com.shopforhome.shopforhomes.Entities.OrderItemsEntity;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemsDao extends JpaRepository<OrderItemsEntity, String> {
    List<OrderItemsEntity> findByOrderId(OrdersEntity order);
}
