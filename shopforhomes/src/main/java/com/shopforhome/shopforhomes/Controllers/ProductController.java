package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Services.ProductServices;
import com.shopforhome.shopforhomes.DTO.ProductDiscountDTO;
import com.shopforhome.shopforhomes.Services.DiscountCouponsService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @Autowired
    private DiscountCouponsService discountCouponsService;


    @GetMapping("get")
    public ResponseEntity<List<ProductsEntity>> getAllProducts(@RequestParam String uid) {
        return productServices.getAllProducts(uid);
    }

    @GetMapping("getallproducts")
    public ResponseEntity<List<ProductsEntity>> getAllProductsWithoutId() {
        return ResponseEntity.ok(productServices.getAllProductsWithoutId());
    }

    @GetMapping("getallproducts/{pid}")
    public ResponseEntity<Optional<ProductsEntity>> getProductById(@PathVariable String pid) {
        return ResponseEntity.ok(productServices.getProductById(pid));
    }

    @GetMapping("get/{pid}")
    public ResponseEntity<ProductsEntity> getProduct(@PathVariable String pid, @RequestParam String uid) {
        return productServices.getProduct(pid, uid);
    }

    @GetMapping("get/{pid}/apply-coupon")
    public ResponseEntity<ProductDiscountDTO> applyDiscountToProdcut(
        @PathVariable String pid, 
        @RequestParam String couponCode,
        @RequestParam String uid
    ) {
        // First validate the coupon for the user
        ResponseEntity<String> validationResult = discountCouponsService.validateCouponForUser(couponCode, uid);
        
        // If coupon is not valid, return the appropriate response
        if (validationResult.getStatusCode() != org.springframework.http.HttpStatus.OK) {
            return new ResponseEntity<>(validationResult.getStatusCode());
        }

        // If coupon is valid, apply it to the product
        return productServices.getProductWithDiscount(pid, couponCode);
    }

    // Add a product
    @PostMapping(value = "add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductsEntity> addProduct(@RequestBody ProductsEntity product) {
        return productServices.addProduct(product);
    }

    @GetMapping("search")
    public List<ProductsEntity> searchProducts(@RequestParam String query) {
        return productServices.searchProducts(query);
    }

    @GetMapping("search/category")
    public List<ProductsEntity> searchByCategory(@RequestParam String category) {
        return productServices.searchByCategory(category);
    }
}