package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.shopforhome.shopforhomes.Dao.WishlistDao;
import com.shopforhome.shopforhomes.Dao.ProductDao;
import com.shopforhome.shopforhomes.Entities.WishlistEntity;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;

@Service
public class WishlistService {

    @Autowired
    private WishlistDao wishlistDao;

    @Autowired
    private ProductDao productDao;

    public ResponseEntity<String> addToWishlist(WishlistEntity wishlist) {
        Optional<ProductsEntity> product = productDao.findById(wishlist.getPid());
        if (!product.isPresent()) {
            wishlist.setProductName(product.get().getName());
            wishlist.setProductDescription(product.get().getDescription());
            wishlist.setCategory(product.get().getCategory());
            wishlistDao.save(wishlist);
        } else {
            // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>("Product Already in the list", HttpStatus.NOT_FOUND);
        }

        // return new ResponseEntity<>(wishlistDao.save(wishlist), HttpStatus.CREATED);
        return new ResponseEntity<>("Product added to wishlist", HttpStatus.CREATED);
    }

    public ResponseEntity<List<WishlistEntity>> getUserWishlist(String uid) {
        List<WishlistEntity> wishlists = wishlistDao.findByUid(uid).stream().map(wishlist -> {
            Optional<ProductsEntity> product = productDao.findById(wishlist.getPid());
            product.ifPresent(p -> {
                wishlist.setProductName(p.getName());
                wishlist.setProductDescription(p.getDescription());
                wishlist.setCategory(p.getCategory());
            });

            return wishlist;
        }).collect(Collectors.toList());
        System.out.println(wishlists);

        return new ResponseEntity<>(wishlists, HttpStatus.OK);
    }

    public ResponseEntity<String> removeFromWishlist(String wid) {
        if (wishlistDao.existsById(wid)) {
            wishlistDao.deleteById(wid);
            return new ResponseEntity<>("Item removed from Wishlist", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item not in Wishlist", HttpStatus.NOT_FOUND);
        }
    }
}
