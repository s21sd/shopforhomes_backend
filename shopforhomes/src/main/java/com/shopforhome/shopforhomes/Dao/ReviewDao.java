package com.shopforhome.shopforhomes.Dao;

import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewDao extends JpaRepository<ReviewEntity, String> {
    List<ReviewEntity> findByProductId(String productId);
    List<ReviewEntity> findByUserId(String userId);
}
