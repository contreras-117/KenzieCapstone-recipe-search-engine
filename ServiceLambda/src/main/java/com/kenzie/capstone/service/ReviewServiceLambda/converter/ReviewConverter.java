package com.kenzie.capstone.service.ReviewServiceLambda.converter;

import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;

public class ReviewConverter {

    public static ReviewResponse fromRecordToResponse(ReviewRecord record) {
        ReviewResponse review = new ReviewResponse();
        review.setReviewerId(record.getReviewerId());
        review.setComment(review.getComment());
        review.setRating(record.getRating());
        review.setRecipeId(record.getRecipeId());
        return review;
    }
}
