package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Dao.ReviewDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    public ReviewEntity addReview(ReviewEntity review) {
        return reviewDao.save(review);
    }

    public List<ReviewEntity> getReviewsByProductId(ProductsEntity product) {
        return reviewDao.findByProductId(product);
    }

    public List<ReviewEntity> getReviewsByUserId(UserEntity user) {
        return reviewDao.findByUserId(user);
    }

    public Optional<ReviewEntity> getReviewById(String rid) {
        return reviewDao.findById(rid);
    }

    public void deleteReview(String rid) {
        reviewDao.deleteById(rid);
    }
}
