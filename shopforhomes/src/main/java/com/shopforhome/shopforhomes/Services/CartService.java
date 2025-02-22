package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.CartDao;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    // Get all items in a user's cart
    public ResponseEntity<List<CartEntity>> getCartByUser(String userId) {
        List<CartEntity> cartItems = cartDao.findByUserId(userId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    // Add item to cart
    public ResponseEntity<CartEntity> addCartItem(CartEntity cartItem) {
        CartEntity savedItem = cartDao.save(cartItem);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // Remove item from cart
    public ResponseEntity<Void> removeCartItem(String cartId) {
        Optional<CartEntity> existingItem = cartDao.findById(cartId);
        if (existingItem.isPresent()) {
            cartDao.deleteById(cartId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
