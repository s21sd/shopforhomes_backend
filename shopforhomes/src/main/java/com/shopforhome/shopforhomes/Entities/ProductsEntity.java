// package com.shopforhome.shopforhomes.Entities;

// import jakarta.persistence.*;
// import lombok.Data;
// import java.util.Arrays;
// import java.util.List;
// import java.util.UUID;

// @Data
// @Entity
// @Table(name = "products")
// public class ProductsEntity {

//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)
//     private String pid;

//     private String name;
//     private String description;
//     private double price;
//     private String category;
//     @Column(name = "image_paths")
//     private String imagePaths; // Store as a string in DB

//     @Transient
//     private List<String> imagePathsList; // Convert for Java use

//     // Getter: Convert stored comma-separated string to List<String>
//     public List<String> getImagePathsList() {
//         if (this.imagePathsList == null && this.imagePaths != null) {
//             this.imagePathsList = Arrays.asList(this.imagePaths.split(","));
//         }
//         return this.imagePathsList;
//     }

//     // Setter: Convert List<String> to comma-separated string
//     public void setImagePathsList(List<String> imagePathsList) {
//         this.imagePathsList = imagePathsList;
//         this.imagePaths = (imagePathsList != null) ? String.join(",", imagePathsList) : null;
//     }

//     private int stock;
// }
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
