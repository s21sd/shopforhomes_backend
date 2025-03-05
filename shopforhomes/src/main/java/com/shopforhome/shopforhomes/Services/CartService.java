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

import java.util.List;
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

    // public ResponseEntity<List<CartResponseDTO>> getAllCartItems() 
    // {
    //     List<CartResponseDTO> response = cartDao.findAll().stream()
    //             .map(cartItem -> new CartResponseDTO(
    //                     cartItem.getUser().getUid(),
    //                     cartItem.getProduct().getPid(),
    //                     cartItem.getProductName(),
    //                     cartItem.getProductPrice(),
    //                     cartItem.getQuantity()
    //             ))
    //             .collect(Collectors.toList());

    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    public ResponseEntity<List<CartResponseDTO>> getCartItemsByUserId(String userId) {
        List<CartResponseDTO> response = cartDao.findByUser_Uid(userId).stream()
                .map(cartItem -> new CartResponseDTO(
                        cartItem.getUser().getUid(),
                        cartItem.getProduct().getPid(),
                        cartItem.getProductName(),
                        cartItem.getProductPrice(),
                        cartItem.getQuantity(),
                        cartItem.getImagePaths()
                ))
                .collect(Collectors.toList());

        return response.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(response, HttpStatus.OK);
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
        cartItem.setProductName(product.get().getName());
        cartItem.setProductPrice(product.get().getPrice());
        cartItem.setImagePaths(product.get().getImagePaths());
        cartItem.setQuantity(Math.max(quantity, 1));

        CartEntity savedCartItem = cartDao.save(cartItem);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);

    }
    public ResponseEntity<Void> removeFromCart(String userId, String pid) {
    Optional<CartEntity> cartItem = cartDao.findByUser_UidAndProduct_Pid(userId, pid);

    if (cartItem.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    cartDao.delete(cartItem.get());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}


}
