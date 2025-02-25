package com.shopforhome.shopforhomes.Dao;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewDao extends JpaRepository<ReviewEntity, String> {
    List<ReviewEntity> findByProductId(ProductsEntity productId);
    List<ReviewEntity> findByUserId(UserEntity userId);
}
