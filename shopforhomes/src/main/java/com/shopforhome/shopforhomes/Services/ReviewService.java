package com.shopforhome.shopforhomes.Services;

import com.shopforhome.shopforhomes.Entities.ProductsEntity;
import com.shopforhome.shopforhomes.Entities.ReviewEntity;
import com.shopforhome.shopforhomes.Entities.UserEntity;
import com.shopforhome.shopforhomes.Dao.ReviewDao;
import com.shopforhome.shopforhomes.Dao.UserDao;
import com.shopforhome.shopforhomes.Dao.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;


    // public ReviewEntity addReview(ReviewEntity review) {
    //     return reviewDao.save(review);
    // }
//     public ReviewEntity addReview(ReviewEntity review) {
//     if (review.getUserId() == null || review.getProductId() == null) {
//         throw new IllegalArgumentException("User and Product cannot be null");
//     }
//     return reviewDao.save(review);
// }

public ReviewEntity addReview(ReviewEntity review) {
    if (review.getUserId() == null || review.getProductId() == null) {
        throw new IllegalArgumentException("User and Product cannot be null");
    }

    // Fetch actual UserEntity and ProductsEntity from the database
    UserEntity user = userDao.findById(review.getUserId().getUid())
            .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
    ProductsEntity product = productDao.findById(review.getProductId().getPid())
            .orElseThrow(() -> new IllegalArgumentException("Invalid Product ID"));

    // Set the fetched objects
    review.setUserId(user);
    review.setProductId(product);

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

