package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.shopforhome.shopforhomes.Entities.WishlistEntity;
import com.shopforhome.shopforhomes.Services.WishlistService;

@RestController
@RequestMapping("api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    
    @PostMapping("add")
    public ResponseEntity<WishlistEntity> addToWishlist(@RequestBody WishlistEntity wishlist) {
        return wishlistService.addToWishlist(wishlist);
    }
 
    @GetMapping("user/{uid}")
    public ResponseEntity<List<WishlistEntity>> getUserWishlist(@PathVariable String uid) {
        return wishlistService.getUserWishlist(uid);
    }

    @DeleteMapping("remove/{wid}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable String wid) {
        return wishlistService.removeFromWishlist(wid);
    }
}
