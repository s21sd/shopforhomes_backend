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
    private OrderAddressDao orderAddressRepository;

    public OrderAddressEntity addOrderAddress(OrderAddressEntity orderAddress) {
        return orderAddressRepository.save(orderAddress);
    }

    public List<OrderAddressEntity> getAllAddresses() {
        return orderAddressRepository.findAll();
    }

    public Optional<OrderAddressEntity> getOrderAddressById(String oaid) {
        return orderAddressRepository.findById(oaid);
    }

    public OrderAddressEntity updateAddress(String oaid, String newAddress) {
        Optional<OrderAddressEntity> optionalAddress = orderAddressRepository.findById(oaid);
        if (optionalAddress.isPresent()) {
            OrderAddressEntity addressEntity = optionalAddress.get();
            addressEntity.setAddress(newAddress);
            return orderAddressRepository.save(addressEntity);
        }
        return null;
    }
}
