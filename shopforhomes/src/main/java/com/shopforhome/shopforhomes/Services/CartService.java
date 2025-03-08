package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.DTO.CartResponseDTO;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<List<CartResponseDTO>> getCartItemsByUserId(String userId) {
        List<CartResponseDTO> response = cartDao.findByUser_Uid(userId).stream()
                .map(cartItem -> new CartResponseDTO(
                        cartItem.getUser().getUid(),
                        cartItem.getProduct().getPid(),
                        cartItem.getProductName(),
                        cartItem.getProductPrice(),
                        cartItem.getQuantity(),
                        cartItem.getImagePaths()))
                .collect(Collectors.toList());

        return response.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> addToCart(String userId, String productId, int quantity) {
        if (productId == null || userId == null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Product ID and User ID must not be null");
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        Optional<UserEntity> user = userDao.findById(userId);
        Optional<ProductsEntity> product = productDao.findById(productId);

        if (user.isEmpty() || product.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User or Product not found");
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

        // Check if the item already exists in the cart
        Optional<CartEntity> existingCartItem = cartDao.findByUser_UidAndProduct_Pid(userId, productId);
        if (existingCartItem.isPresent()) {
            // If the item exists, update the quantity
            CartEntity cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity); // Increment the quantity
            cartDao.save(cartItem);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Item quantity updated in cart");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // If the item does not exist, add it to the cart
            CartEntity cartItem = new CartEntity();
            cartItem.setUser(user.get());
            cartItem.setProduct(product.get());
            cartItem.setProductName(product.get().getName());
            cartItem.setProductPrice(product.get().getPrice());
            cartItem.setImagePaths(product.get().getImagePaths());
            cartItem.setQuantity(Math.max(quantity, 1)); // Ensure quantity is at least 1

            cartDao.save(cartItem);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Item added to cart successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<Map<String, String>> removeFromCart(String userId, String pid) {
        Optional<CartEntity> cartItem = cartDao.findByUser_UidAndProduct_Pid(userId, pid);
    
        Map<String, String> response = new HashMap<>();
    
        if (cartItem.isEmpty()) {
            response.put("message", "Cart item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    
        cartDao.delete(cartItem.get());
    
        response.put("message", "Cart item deleted successfully");
        return ResponseEntity.ok(response);
    }
}