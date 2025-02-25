package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import java.util.Optional;

public interface DiscountCouponsDao extends JpaRepository<DiscountCouponsEntity, String> {
    Optional<DiscountCouponsEntity> findByCode(String code);
}
