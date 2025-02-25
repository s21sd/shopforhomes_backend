package com.shopforhome.shopforhomes.Controllers;

import com.shopforhome.shopforhomes.Entities.OrderItemsEntity;
import com.shopforhome.shopforhomes.Services.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemsController {

    @Autowired
    private OrderItemsService orderItemsService;

    @PostMapping("/add")
    public OrderItemsEntity addOrderItem(@RequestBody OrderItemsEntity orderItem) {
        return orderItemsService.addOrderItem(orderItem);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItemsEntity> getItemsByOrder(@PathVariable String orderId) {
        return orderItemsService.getItemsByOrderId(orderId);
    }

    @DeleteMapping("/delete/{oiid}")
    public void deleteOrderItem(@PathVariable String oiid) {
        orderItemsService.deleteOrderItem(oiid);
    }
}
