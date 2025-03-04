package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String pid;

    private String name;
    private String description;
    private double price;
    private String category;

    @Column(name = "image_paths")
    private String imagePaths; 

    @Transient
    private List<String> imagePathsList; 

    private int stock;

    // Convert DB string to List after fetching
    @PostLoad
    private void convertStringToList() {
        if (this.imagePaths != null && !this.imagePaths.isEmpty()) {
            this.imagePathsList = Arrays.asList(this.imagePaths.split(","));
        }
    }

    // Convert List to String before saving to DB
    @PrePersist
    @PreUpdate
    private void convertListToString() {
        if (this.imagePathsList != null) {
            this.imagePaths = String.join(",", this.imagePathsList);
        }
    }
}
