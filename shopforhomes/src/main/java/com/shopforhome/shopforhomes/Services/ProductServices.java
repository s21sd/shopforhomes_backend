package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.ProductDao;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.DTO.ProductDiscountDTO;
import com.shopforhome.shopforhomes.Entities.DiscountCouponsEntity;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Dao.DiscountCouponsDao;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServices {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DiscountCouponsService discountCouponsService;


    @Autowired
    private DiscountCouponsDao discountCouponsDao;
    

    // Get all products
    public ResponseEntity<List<ProductsEntity>> getAllProducts(String uid) {
        Optional<UserEntity> user = userDao.findById(uid);
        if (user.isEmpty() || !user.get().getRole().equalsIgnoreCase("Admin")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden if not Admin
        }

        List<ProductsEntity> products = productDao.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    public ResponseEntity<ProductsEntity> getProduct(String pid, String uid) {
        Optional<UserEntity> user = userDao.findById(uid);
        if (user.isEmpty() || !user.get().getRole().equalsIgnoreCase("Admin")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden if not Admin
        }

        ProductsEntity product = productDao.findById(pid).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    // Add a new product
    public ResponseEntity<ProductsEntity> addProduct(ProductsEntity product) {
        ProductsEntity savedProduct = productDao.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    public List<ProductsEntity> searchProducts(String query) {
        return productDao.searchProducts(query);
    }
    public List<ProductsEntity> searchByCategory(String category) {
        return productDao.searchByCategory(category);
    }
    
    public List<ProductsEntity> getAllProductsWithoutId() {
        return productDao.findAll();
    }

    public Optional<ProductsEntity> getProductById(String pid) {
        return productDao.findById(pid);
    }

    public ResponseEntity<ProductDiscountDTO> getProductWithDiscount(String pid, String couponCode) {
        // Find the product
        Optional<ProductsEntity> productOptional = productDao.findById(pid);
        if (productOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductsEntity product = productOptional.get();

        // Find the coupon
        Optional<DiscountCouponsEntity> couponOptional = discountCouponsDao.findByCode(couponCode);
        if (couponOptional.isEmpty()) {
            // If no coupon found, return product without discount
            return new ResponseEntity<>(
                new ProductDiscountDTO(product, null), 
                HttpStatus.OK
            );
        }

        DiscountCouponsEntity coupon = couponOptional.get();

        // Create and return product with discount
        return new ResponseEntity<>(
            new ProductDiscountDTO(product, coupon), 
            HttpStatus.OK
        );
    }

    // Get all products with potential discounts
    public ResponseEntity<List<ProductDiscountDTO>> getAllProductsWithDiscounts() {
        List<ProductsEntity> products = productDao.findAll();
        
        List<ProductDiscountDTO> productsWithDiscounts = products.stream()
            .map(product -> new ProductDiscountDTO(product, null))
            .collect(Collectors.toList());
        
        return new ResponseEntity<>(productsWithDiscounts, HttpStatus.OK);
    }
}
