package com.shopforhome.shopforhomes.DTOs;

import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import java.time.LocalDateTime;

public class ReviewDTO {
    private String rid;
    private String uid;
    private String pid;
    private int rating;
    private String reviewText;
    private LocalDateTime createdAt;

    public ReviewDTO(ReviewEntity review) {
        this.rid = review.getRid();
        this.uid = review.getUserId().getUid();  // Extract only UID
        this.pid = review.getProductId().getPid();  // Extract only PID
        this.rating = review.getRating();
        this.reviewText = review.getReviewText();
        this.createdAt = review.getCreatedAt();
    }

    public String getRid() { return rid; }
    public String getUid() { return uid; }
    public String getPid() { return pid; }
    public int getRating() { return rating; }
    public String getReviewText() { return reviewText; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
