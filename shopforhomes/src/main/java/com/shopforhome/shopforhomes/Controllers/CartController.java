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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // @GetMapping
    // public ResponseEntity<List<CartResponseDTO>> getCartItems() {
    //     return cartService.getAllCartItems();
    // }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartResponseDTO>> getCartItemsByUser(@PathVariable String userId) {
        return cartService.getCartItemsByUserId(userId);
    }

    @PostMapping("/add")
    public ResponseEntity<CartEntity> addToCart(@RequestBody CartRequest request) {
    return cartService.addToCart(request.getUserId(), request.getProductId(), request.getQuantity());
    }

}
