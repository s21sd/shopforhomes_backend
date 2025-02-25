package com.shopforhome.shopforhomes.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

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
    private String imagePathsString;  

    @Transient
    private List<String> imagePaths;

    public List<String> getImagePaths() {
        return imagePathsString != null ? Arrays.asList(imagePathsString.split(",")) : null;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
        this.imagePathsString = (imagePaths != null) ? String.join(",", imagePaths) : null;
    }

    private int stock;
}
