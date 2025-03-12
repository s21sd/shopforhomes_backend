package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.Entities.OrderAddressEntity;
import com.shopforhome.shopforhomes.Dao.OrderAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;

@Service
public class OrderAddressService {

    @Autowired
    private OrderAddressDao orderAddressDao;

    public OrderAddressEntity addOrderAddress(OrderAddressEntity orderAddress) {
        return orderAddressDao.save(orderAddress);
    }

    public List<OrderAddressEntity> getAllAddresses() {
        return orderAddressDao.findAll();
    }

    public Optional<OrderAddressEntity> getOrderAddressById(String oaid) {
        return orderAddressDao.findById(oaid);
    }

    public OrderAddressEntity updateAddress(String oaid, String newAddress) {
        Optional<OrderAddressEntity> optionalAddress = orderAddressDao.findById(oaid);
        if (optionalAddress.isPresent()) {
            OrderAddressEntity addressEntity = optionalAddress.get();
            addressEntity.setAddress(newAddress);
            return orderAddressDao.save(addressEntity);
        }
        return null;
    }
}
