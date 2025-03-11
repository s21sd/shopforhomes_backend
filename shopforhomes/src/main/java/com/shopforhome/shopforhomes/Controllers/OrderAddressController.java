package com.shopforhome.shopforhomes.Controllers;

import com.shopforhome.shopforhomes.DTO.OrderAddressDTO;
import com.shopforhome.shopforhomes.Entities.OrderAddressEntity;
import com.shopforhome.shopforhomes.Entities.OrdersEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Services.OrderAddressService;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Dao.OrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order-address")
public class OrderAddressController {

    @Autowired
    private OrderAddressService orderAddressService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrdersDao orderDao;

    @PostMapping("/add")
    public ResponseEntity<?> addOrderAddress(@RequestBody OrderAddressDTO dto) {
        Optional<UserEntity> user = userDao.findById(dto.getUid());
        Optional<OrdersEntity> order = orderDao.findById(dto.getOid());

        if (user.isEmpty() || order.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid user ID or order ID");
        }

        OrderAddressEntity entity = new OrderAddressEntity();
        entity.setUser(user.get());
        entity.setOrder(order.get());
        entity.setAddress(dto.getAddress());

        return ResponseEntity.ok(orderAddressService.addOrderAddress(entity));
    }

    @GetMapping("/all")
    public List<OrderAddressDTO> getAllAddresses() {
        List<OrderAddressEntity> entities = orderAddressService.getAllAddresses();
        List<OrderAddressDTO> dtos = new ArrayList<>();

        for (OrderAddressEntity entity : entities) {
            OrderAddressDTO dto = new OrderAddressDTO();
            dto.setUid(entity.getUser().getUid());
            dto.setOid(entity.getOrder().getOid());
            dto.setAddress(entity.getAddress());
            dtos.add(dto);
        }

        return dtos;
    }

    @GetMapping("/{oaid}")
    public ResponseEntity<?> getOrderAddressById(@PathVariable String oaid) {
        Optional<OrderAddressEntity> addressOpt = orderAddressService.getOrderAddressById(oaid);
        if (addressOpt.isEmpty()) return ResponseEntity.notFound().build();

        OrderAddressEntity entity = addressOpt.get();
        OrderAddressDTO dto = new OrderAddressDTO();
        dto.setUid(entity.getUser().getUid());
        dto.setOid(entity.getOrder().getOid());
        dto.setAddress(entity.getAddress());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/update/{oaid}")
    public ResponseEntity<?> updateOrderAddress(@PathVariable String oaid, @RequestBody Map<String, String> request) {
    String newAddress = request.get("newAddress");

    if (newAddress == null || newAddress.isEmpty()) {
        return ResponseEntity.badRequest().body("New address must not be empty");
    }

    OrderAddressEntity updated = orderAddressService.updateAddress(oaid, newAddress);
    if (updated == null) {
        return ResponseEntity.notFound().build();
    }

    OrderAddressDTO dto = new OrderAddressDTO();
    dto.setUid(updated.getUser().getUid());
    dto.setOid(updated.getOrder().getOid());
    dto.setAddress(updated.getAddress());

    return ResponseEntity.ok(dto);
}

}
