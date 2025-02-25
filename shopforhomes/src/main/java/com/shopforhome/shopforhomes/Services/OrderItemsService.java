package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.Entities.OrderItemsEntity;
import com.shopforhome.shopforhomes.Dao.OrderItemsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemsDao orderItemsDao;

    public OrderItemsEntity addOrderItem(OrderItemsEntity orderItem) {
        return orderItemsDao.save(orderItem);
    }

    public List<OrderItemsEntity> getItemsByOrderId(String orderId) {
        return orderItemsDao.findByOrderId(orderId);
    }

    public void deleteOrderItem(String oiid) {
        orderItemsDao.deleteById(oiid);
    }
}
