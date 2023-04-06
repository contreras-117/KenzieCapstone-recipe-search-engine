package com.kenzie.capstone.service.ReviewServiceLambda;

import com.kenzie.capstone.service.ReviewServiceLambda.dao.ReviewDao;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewData;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewService {

    private ReviewDao reviewDao;

    @Inject
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public List<Review> getRecipeReviews(String recipeId) {
        List<ReviewRecord> records = reviewDao.getRecipeReviews(recipeId);

        return records.stream()
                .map(reviewInstance -> {
                    Review review = new Review();
                    review.setReviewerId(reviewInstance.getReviewerId());
                    review.setRecipeId(reviewInstance.getRecipeId());
                    review.setRating(reviewInstance.getRating());
                    review.setComment(reviewInstance.getComment());
                    return review;
                })
                .collect(Collectors.toList());
    }

    public ReviewData addReview(ReviewCreateRequest reviewCreateRequest) {
        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setRecipeId(reviewCreateRequest.getRecipeId());
        reviewRecord.setReviewerId(reviewCreateRequest.getReviewerId());
        reviewRecord.setRating(reviewCreateRequest.getRating());
        reviewRecord.setComment(reviewCreateRequest.getComment());
        reviewDao.addReview(reviewRecord);
        return new ReviewData(reviewCreateRequest.getRecipeId(),
                reviewCreateRequest.getReviewerId(),
                reviewCreateRequest.getRating(),
                reviewCreateRequest.getComment());
    }
}
