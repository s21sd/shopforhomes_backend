package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import java.util.Optional;
import java.util.List;

public interface DiscountCouponsDao extends JpaRepository<DiscountCouponsEntity, String> {
    // Optional<DiscountCouponsEntity> findByCodeAndUser(String code, UserEntity user); 
    List<DiscountCouponsEntity> findByUser(UserEntity user);  
    Optional<DiscountCouponsEntity> findByCode(String code);
    Optional<DiscountCouponsEntity> findByDiscountId(String discountId);
}
