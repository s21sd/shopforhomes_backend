package com.shopforhome.shopforhomes.Controllers;

import com.shopforhome.shopforhomes.DTO.CartRequest;
import com.shopforhome.shopforhomes.DTO.CartResponseDTO;
import com.shopforhome.shopforhomes.Entities.CartEntity;
import com.shopforhome.shopforhomes.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartResponseDTO>> getCartItemsByUser(@PathVariable String userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity());
    }

    @DeleteMapping("/remove/{userId}/{pid}")
    public ResponseEntity<String> removeFromCart(@PathVariable String userId, @PathVariable String pid) {
        return cartService.removeFromCart(userId, pid);
    }
}
