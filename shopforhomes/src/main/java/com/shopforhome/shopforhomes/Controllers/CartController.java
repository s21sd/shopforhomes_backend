package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import com.shopforhome.shopforhomes.Services.CartService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get all cart items for a specific user
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartEntity>> getUserCart(@PathVariable String userId) {
        return cartService.getCartByUser(userId);
    }

    // Add item to cart
    @PostMapping("/add")
    public ResponseEntity<CartEntity> addToCart(@RequestBody Map<String, Object> requestBody) {
        String userId = (String) requestBody.get("userId");
        String productId = (String) requestBody.get("productId");
        int quantity = (int) requestBody.getOrDefault("quantity", 1);
        
        return cartService.addCartItem(userId, productId, quantity);
    }

    // Remove item from cart
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable String cartId) {
        return cartService.removeCartItem(cartId);
    }
}
