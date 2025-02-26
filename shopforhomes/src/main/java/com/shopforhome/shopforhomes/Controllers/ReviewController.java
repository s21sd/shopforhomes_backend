package com.shopforhome.shopforhomes.Controllers;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Services.ReviewService;
import com.shopforhome.shopforhomes.Services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
=======
import com.shopforhome.shopforhomes.DTO.ReviewDTO;
>>>>>>> 5e7b030 (changes)
// import com.shopforhome.shopforhomes.DTO.ReviewDTO;


// import java.util.List;
import java.util.Optional;

// @RestController
// @RequestMapping("/reviews")
// public class ReviewController {

//     @Autowired
//     private ReviewService reviewService;

//     @Autowired
//     private ProductServices productServices;

    

    // @GetMapping("/product/{productId}")
    // public List<ReviewEntity> getReviewsByProduct(@PathVariable ProductsEntity product) {
    //     return reviewService.getReviewsByProductId(product);
    // }

    // @GetMapping("/user/{userId}")
    // public List<ReviewEntity> getReviewsByUser(@PathVariable UserEntity user) {
    //     return reviewService.getReviewsByUserId(user);
    // }
//     @GetMapping("/product/{productId}")
// public List<ReviewEntity> getReviewsByProduct(@PathVariable String productId) {
//     ProductsEntity product = new ProductsEntity();
//     product.setPid(productId);
//     return reviewService.getReviewsByProductId(product);
// }

// @GetMapping("/user/{userId}")
// public List<ReviewEntity> getReviewsByUser(@PathVariable String userId) {
//     UserEntity user = new UserEntity();
//     user.setUid(userId);
//     return reviewService.getReviewsByUserId(user);
// }

<<<<<<< HEAD
import com.shopforhome.shopforhomes.DTO.ReviewDTO;
import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

=======
>>>>>>> 5e7b030 (changes)
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductServices productServices;

    @PostMapping("/add")
    public ReviewEntity addReview(@RequestBody ReviewEntity review) {
        return reviewService.addReview(review);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewDTO> getReviewsByUser(@PathVariable String userId) {
        UserEntity user = new UserEntity();
        user.setUid(userId);
        List<ReviewEntity> reviews = reviewService.getReviewsByUserId(user);
        return reviews.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/product/{productId}")
    public List<ReviewDTO> getReviewsByProduct(@PathVariable String productId) {
        ProductsEntity product = new ProductsEntity();
        product.setPid(productId);
        List<ReviewEntity> reviews = reviewService.getReviewsByProductId(product);
        return reviews.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{rid}")
    public Optional<ReviewDTO> getReviewById(@PathVariable String rid) {
        // return reviewService.getReviewById(rid).map(ReviewDTO::new).orElseThrow(()-> new IllegalArgumentException("Invalid Review ID"));
        return reviewService.getReviewById(rid).map(ReviewDTO::new);
    }

    @DeleteMapping("/delete/{rid}")
    public void deleteReview(@PathVariable String rid) {
        reviewService.deleteReview(rid);
    }

}