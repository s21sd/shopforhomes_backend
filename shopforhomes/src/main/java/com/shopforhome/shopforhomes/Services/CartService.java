package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.CartDao;
import com.shopforhome.shopforhomes.Dao.ProductDao;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    // Get all items in a user's cart
    public ResponseEntity<List<CartEntity>> getCartByUser(String userId) {
        Optional<UserEntity> user = userDao.findById(userId);
        if (user.isPresent()) {
            List<CartEntity> cartItems = cartDao.findByUser(user.get());
            return new ResponseEntity<>(cartItems, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add item to cart
    public ResponseEntity<CartEntity> addCartItem(String userId, String productId, int quantity) {
        Optional<UserEntity> user = userDao.findById(userId);
        Optional<ProductsEntity> product = productDao.findById(productId);

        if (user.isEmpty() || product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CartEntity cartItem = new CartEntity();
        cartItem.setUser(user.get());
        cartItem.setProduct(product.get());
        cartItem.setProductPrice(product.get().getPrice());
        cartItem.setQuantity(quantity);

        CartEntity savedItem = cartDao.save(cartItem);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    // Remove item from cart
    public ResponseEntity<Void> removeCartItem(String cartId) {
        if (cartDao.existsById(cartId)) {
            cartDao.deleteById(cartId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
