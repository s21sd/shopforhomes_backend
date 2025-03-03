package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import java.util.List;

public interface CartDao extends JpaRepository<CartEntity, String> {
    List<CartEntity> findByUser_Uid(String uid); 
}
