package com.shopforhome.shopforhomes.Controllers;

import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    public ReviewEntity addReview(@RequestBody ReviewEntity review) {
        return reviewService.addReview(review);
    }

    @GetMapping("/product/{productId}")
    public List<ReviewEntity> getReviewsByProduct(@PathVariable String productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public List<ReviewEntity> getReviewsByUser(@PathVariable String userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @GetMapping("/{rid}")
    public Optional<ReviewEntity> getReviewById(@PathVariable String rid) {
        return reviewService.getReviewById(rid);
    }

    @DeleteMapping("/delete/{rid}")
    public void deleteReview(@PathVariable String rid) {
        reviewService.deleteReview(rid);
    }
}
