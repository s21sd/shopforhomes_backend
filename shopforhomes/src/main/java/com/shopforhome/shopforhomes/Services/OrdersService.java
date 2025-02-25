package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.OrdersDao;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Entities.OrderStatus;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    // Get all orders for a user
    public ResponseEntity<List<OrdersEntity>> getOrdersByUser(UserEntity userId) {
        List<OrdersEntity> orders = ordersDao.findByUserId(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Place a new order
    public ResponseEntity<OrdersEntity> placeOrder(OrdersEntity order) {
        order.setStatus(OrderStatus.PENDING); // Default status
        OrdersEntity savedOrder = ordersDao.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Update order status
    public ResponseEntity<OrdersEntity> updateOrderStatus(String orderId, OrderStatus status) {
        Optional<OrdersEntity> existingOrder = ordersDao.findById(orderId);
        if (existingOrder.isPresent()) {
            OrdersEntity order = existingOrder.get();
            order.setStatus(status);
            ordersDao.save(order);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
