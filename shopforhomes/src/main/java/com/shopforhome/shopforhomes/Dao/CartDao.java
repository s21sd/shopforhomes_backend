package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import java.util.List;
import java.util.Optional;

public interface CartDao extends JpaRepository<CartEntity, String> {
    List<CartEntity> findByUser_Uid(String uid); 
    Optional<CartEntity> findByUser_UidAndProduct_Pid(String uid, String pid);
}
