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

        return response.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<String> addToCart(String userId, String productId, int quantity) {
        if (productId == null || userId == null) {
            return new ResponseEntity<>("Product ID and User ID must not be null", HttpStatus.BAD_REQUEST);
        }

        Optional<UserEntity> user = userDao.findById(userId);
        Optional<ProductsEntity> product = productDao.findById(productId);

        if (user.isEmpty() || product.isEmpty()) {
            return new ResponseEntity<>("User or Product not found", HttpStatus.NOT_FOUND);
        }

        Optional<CartEntity> existingCartItem = cartDao.findByUser_UidAndProduct_Pid(userId, productId);
        if (existingCartItem.isPresent()) {
            return new ResponseEntity<>("Item is already in the cart", HttpStatus.CONFLICT);
        }

        CartEntity cartItem = new CartEntity();
        cartItem.setUser(user.get());
        cartItem.setProduct(product.get());
        cartItem.setProductName(product.get().getName());
        cartItem.setProductPrice(product.get().getPrice());
        cartItem.setImagePaths(product.get().getImagePaths());
        cartItem.setQuantity(Math.max(quantity, 1));

        cartDao.save(cartItem);
        return new ResponseEntity<>("Item added to cart successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeFromCart(String userId, String pid) {
        Optional<CartEntity> cartItem = cartDao.findByUser_UidAndProduct_Pid(userId, pid);

        if (cartItem.isEmpty()) {
            return new ResponseEntity<>("Cart Item not found", HttpStatus.NOT_FOUND);
        }

        cartDao.delete(cartItem.get());
        return new ResponseEntity<>("Cart Item successfully removed", HttpStatus.OK);
    }
}
