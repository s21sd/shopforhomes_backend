package com.shopforhome.shopforhomes.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopforhome.shopforhomes.Dao.ProductDao;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductDao productDao;

    // Get all products
    public ResponseEntity<List<ProductsEntity>> getAllProducts() {
        List<ProductsEntity> products = productDao.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Add a new product
    public ResponseEntity<ProductsEntity> addProduct(ProductsEntity product) {
        ProductsEntity savedProduct = productDao.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
