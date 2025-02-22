package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import java.util.List;

public interface OrdersDao extends JpaRepository<OrdersEntity, String> {
    List<OrdersEntity> findByUserId(String userId);
}
