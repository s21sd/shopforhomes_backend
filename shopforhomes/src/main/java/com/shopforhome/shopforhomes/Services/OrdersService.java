package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopforhome.shopforhomes.DTO.OrderDTO;
import com.shopforhome.shopforhomes.Dao.OrdersDao;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
// import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Entities.OrderStatus;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;
@Service
public class OrdersService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrdersDao ordersDao;

    // Get all orders for a user
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(String userId) {
        List<OrdersEntity> orders = ordersDao.findByUser_Uid(userId);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> new OrderDTO(order.getOid(), order.getUser().getUid(), order.getTotalPrice(), order.getStatus(), order.getCreatedAt()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }


    // Place a new order
<<<<<<< HEAD
    // public ResponseEntity<OrdersEntity> placeOrder(OrdersEntity order) {
    // if (order.getUser() == null || order.getUser().getUid() == null) {
    //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    // }
    // Optional<UserEntity> userOpt = userDao.findById(order.getUser().getUid());
    // if (userOpt.isEmpty()) {
    //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // order.setUser(userOpt.get());
    // order.setStatus(OrderStatus.PENDING);
    // OrdersEntity savedOrder = ordersDao.save(order);
    // return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    // }
    public ResponseEntity<OrdersEntity> placeOrder(Map<String, Object> orderData) {
    String uid = (String) orderData.get("uid");
    Double totalPrice = ((Number) orderData.get("totalPrice")).doubleValue();
    
    if (uid == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    Optional<UserEntity> userOpt = userDao.findById(uid);
    if (userOpt.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    OrdersEntity order = new OrdersEntity();
    order.setUser(userOpt.get());
    order.setTotalPrice(totalPrice);
    order.setStatus(OrderStatus.PENDING);

    OrdersEntity savedOrder = ordersDao.save(order);
    return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
}

=======
    public ResponseEntity<OrdersEntity> placeOrder(OrdersEntity order) 
    {
        // if (order.getUser() == null || order.getUser().getUid() == null) {
        //     return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        // }
        Optional<UserEntity> userOpt = userDao.findById(order.getUser().getUid());
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setUser(userOpt.get());
        order.setStatus(OrderStatus.PENDING);
        OrdersEntity savedOrder = ordersDao.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
>>>>>>> 5e7b030 (changes)

    // Fetch all the orders
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrdersEntity> orders = ordersDao.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> new OrderDTO(order.getOid(), order.getUser().getUid(), order.getTotalPrice(), order.getStatus(), order.getCreatedAt()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
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
