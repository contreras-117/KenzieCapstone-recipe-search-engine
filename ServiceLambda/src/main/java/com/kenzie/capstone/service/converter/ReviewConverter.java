package com.kenzie.capstone.service.converter;

import com.kenzie.capstone.service.model.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewResponse;

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
