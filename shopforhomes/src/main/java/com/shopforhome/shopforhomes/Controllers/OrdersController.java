package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.DTO.OrderDTO;
import com.shopforhome.shopforhomes.Entities.OrderStatus;
import com.shopforhome.shopforhomes.Services.OrdersService;
import java.util.List;

import java.util.Map;
@RestController
@RequestMapping("api/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    // Get all orders
    // @GetMapping("/all")
    // public ResponseEntity<List<OrderDTO>> getAllOrders() {
    //     return ordersService.getAllOrders();
    // }

    // Get all orders for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<List<OrderDTO>> getUserOrders(@PathVariable String userId) {
        return ordersService.getOrdersByUser(userId);
    }

    // Place a new order
    // @PostMapping("/place")
    // public ResponseEntity<OrdersEntity> placeOrder(@RequestBody OrdersEntity order) {
    //     return ordersService.placeOrder(order);
    // }
    
    @PostMapping("/place")
    public ResponseEntity<OrdersEntity> placeOrder(@RequestBody Map<String, Object> orderData) {
    return ordersService.placeOrder(orderData);
    }


    // Update order status
    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrdersEntity> updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatus status) {
        return ordersService.updateOrderStatus(orderId, status);
    }
}
