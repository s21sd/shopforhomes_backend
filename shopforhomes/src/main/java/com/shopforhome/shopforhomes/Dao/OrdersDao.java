package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
// import com.shopforhome.shopforhomes.Entities.UserEntity;

import java.util.List;

public interface OrdersDao extends JpaRepository<OrdersEntity, String> {
    List<OrdersEntity> findByUser_Uid(String uid);
}
