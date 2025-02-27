package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import com.shopforhome.shopforhomes.Dao.WishlistDao;
import com.shopforhome.shopforhomes.Entities.WishlistEntity;

@Service
public class WishlistService {

    @Autowired
    private WishlistDao wishlistDao;

    public ResponseEntity<WishlistEntity> addToWishlist(WishlistEntity wishlist) {
        return new ResponseEntity<>(wishlistDao.save(wishlist), HttpStatus.CREATED);
    }

    public ResponseEntity<List<WishlistEntity>> getUserWishlist(String uid) {
        List<WishlistEntity> wishlists = wishlistDao.findByUid(uid);
        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    
    public ResponseEntity<Void> removeFromWishlist(String wid) {
        if (wishlistDao.existsById(wid)) {
            wishlistDao.deleteById(wid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
