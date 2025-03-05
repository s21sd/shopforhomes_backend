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

    // Get all orders for a user
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(String userId) {
        List<OrdersEntity> orders = ordersDao.findByUser_Uid(userId);
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> new OrderDTO(order.getOid(), order.getUser().getUid(), order.getTotalPrice(), order.getStatus(), order.getCreatedAt()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }




    // place a order
    public ResponseEntity<OrdersEntity> placeOrder(Map<String, Object> orderData) {
    String uid = (String) orderData.get("uid");
    Double totalPrice = ((Number) orderData.get("totalPrice")).doubleValue();
    String couponCode = (String) orderData.get("couponCode");
    
        if (uid == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<UserEntity> userOpt = userDao.findById(uid);
        if (userOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (couponCode != null && !couponCode.trim().isEmpty()) {
            ResponseEntity<String> validationResponse = discountCouponsService.validateCouponForUser(couponCode, uid);
            if (validationResponse.getStatusCode() != HttpStatus.OK) {
                return new ResponseEntity<>(validationResponse.getStatusCode());
            }

            Optional<DiscountCouponsEntity> couponOpt = discountCouponsDao.findByCode(couponCode);
            if (couponOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            DiscountCouponsEntity coupon = couponOpt.get();
            double discountAmount = coupon.getDiscount();
            totalPrice = Math.max(0, totalPrice - discountAmount);
        }


        OrdersEntity order = new OrdersEntity();
        order.setUser(userOpt.get());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        OrdersEntity savedOrder = ordersDao.save(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }
 

    public ResponseEntity<OrdersEntity> updateOrderStatus(String orderId, OrderStatus status) {
        Optional<OrdersEntity> orderOpt = ordersDao.findById(orderId);
        
        if (orderOpt.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Order not found
        }
    
        OrdersEntity order = orderOpt.get();
        order.setStatus(status);
        ordersDao.save(order);
    
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    
}
