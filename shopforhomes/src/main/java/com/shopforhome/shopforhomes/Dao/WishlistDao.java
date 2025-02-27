package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.shopforhome.shopforhomes.Entities.WishlistEntity;

public interface WishlistDao extends JpaRepository<WishlistEntity, String> {
    List<WishlistEntity> findByUid(String uid); 
}
