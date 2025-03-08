package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shopforhome.shopforhomes.DTO.OrderDTO;
import com.shopforhome.shopforhomes.Dao.OrdersDao;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Dao.CartDao;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
// import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Entities.OrderStatus;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Dao.DiscountCouponsDao;
import com.shopforhome.shopforhomes.Services.DiscountCouponsService;
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

    @Autowired
    private DiscountCouponsService discountCouponsService;

    @Autowired
    private DiscountCouponsDao discountCouponsDao;

    @Autowired
    private CartDao cartDao;

    // Fetch cart items for a user and calculate the total price
    private double calculateTotalPriceFromCart(String userId) {
        List<CartEntity> cartItems = cartDao.findByUser_Uid(userId);
        return cartItems.stream()
                .mapToDouble(cartItem -> cartItem.getProductPrice() * cartItem.getQuantity())
                .sum();
    }

    // Place an order by fetching prices from the cart table
    public ResponseEntity<OrdersEntity> placeOrder(String userId) {
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<UserEntity> userOpt = userDao.findById(userId);
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Calculate the total price from the cart
        double totalPrice = calculateTotalPriceFromCart(userId);

        // Create a new order
        OrdersEntity order = new OrdersEntity();
        order.setUser(userOpt.get());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        OrdersEntity savedOrder = ordersDao.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    // Get all orders for a user with status PENDING
    public ResponseEntity<List<OrderDTO>> getPendingOrdersByUser(String userId) {
        List<OrdersEntity> pendingOrders = ordersDao.findByUser_UidAndStatus(userId, OrderStatus.PENDING);
        List<OrderDTO> orderDTOs = pendingOrders.stream()
                .map(order -> new OrderDTO(
                        order.getOid(),
                        order.getUser().getUid(),
                        order.getTotalPrice(),
                        order.getStatus(),
                        order.getCreatedAt()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    // Update order status
    public ResponseEntity<OrdersEntity> updateOrderStatus(String orderId, OrderStatus status) {
        Optional<OrdersEntity> orderOpt = ordersDao.findById(orderId);

        if (orderOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Order not found
        }

        OrdersEntity order = orderOpt.get();
        order.setStatus(status);
        ordersDao.save(order);

        // If the status is "COMPLETED", delete the corresponding cart items
        if (status == OrderStatus.COMPLETED) {
            List<CartEntity> cartItems = cartDao.findByUser_Uid(order.getUser().getUid());
            cartDao.deleteAll(cartItems);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}