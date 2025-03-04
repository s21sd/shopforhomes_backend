package com.shopforhome.shopforhomes.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Services.ProductServices;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    // Get all products from the

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

    // Add a product
    @PostMapping(value = "add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductsEntity> addProduct(@RequestBody ProductsEntity product) {
        return productServices.addProduct(product);
    }

    @GetMapping("search")
    public List<ProductsEntity> searchProducts(@RequestParam String query) {
        return productServices.searchProducts(query);
    }
}