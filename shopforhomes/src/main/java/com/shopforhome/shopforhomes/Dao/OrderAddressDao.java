package com.shopforhome.shopforhomes.Dao;

import com.shopforhome.shopforhomes.Entities.OrderAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressDao extends JpaRepository<OrderAddressEntity, String> 
{
    
}
