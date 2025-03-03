package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.Dao.CartDao;
import com.shopforhome.shopforhomes.Dao.ProductDao;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Dao.UserDao;
// import com.shopforhome.shopforhomes.Dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<List<CartEntity>> getAllCartItems() {
        return new ResponseEntity<>(cartDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<CartEntity>> getCartItemsByUserId(String userId) {
        List<CartEntity> cartItems = cartDao.findByUser_Uid(userId);
        return cartItems.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    public ResponseEntity<CartEntity> addToCart(String userId, String productId, int quantity) {
        if (productId == null || userId == null) {
            throw new IllegalArgumentException("Product ID and User ID must not be null");
        }
        
        Optional<UserEntity> user = userDao.findById(userId);
        Optional<ProductsEntity> product = productDao.findById(productId);

        if (user.isEmpty() || product.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
        }

        CartEntity cartItem = new CartEntity();
        cartItem.setUser(user.get());
        cartItem.setProduct(product.get());

        // Auto-fetch product name and price from ProductsEntity
        cartItem.setProductName(product.get().getName());
        cartItem.setProductPrice(product.get().getPrice());

        // Ensure quantity is valid (at least 1)
        cartItem.setQuantity(Math.max(quantity, 1));

        CartEntity savedCartItem = cartDao.save(cartItem);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }
}
