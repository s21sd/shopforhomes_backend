package com.shopforhome.shopforhomes.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface ProductDao extends JpaRepository<ProductsEntity, String> 
{
    // Full-text search on product name and description
    @Query("SELECT p FROM ProductsEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<ProductsEntity> searchProducts(String query);

    // Autocomplete suggestions based on product name
    // @Query("SELECT p.name FROM ProductsEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT(:query, '%'))")
    // List<String> autocomplete(String query);
}
