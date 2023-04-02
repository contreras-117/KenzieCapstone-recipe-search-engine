package com.kenzie.capstone.service.ReviewServiceLambda;

import com.kenzie.capstone.service.ReviewServiceLambda.dao.ReviewDao;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewData;

import javax.inject.Inject;

public class ReviewService {

    private ReviewDao reviewDao;

    @Inject
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    public ReviewData getExampleData(String id) {
//        List<ReviewRecord> records = reviewDao.getExampleData(id);
//        if (records.size() > 0) {
//            return new ExampleData(records.get(0).getId(), records.get(0).getData());
//        }
        return null;
    }

    public ReviewData addReview(ReviewCreateRequest reviewCreateRequest) {
        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setRecipeId(reviewCreateRequest.getRecipeId());
        reviewRecord.setReviewerId(reviewCreateRequest.getReviewerId());
        reviewRecord.setRating(reviewCreateRequest.getRating());
        reviewRecord.setComment(reviewCreateRequest.getComment());
        ReviewRecord record = reviewDao.addReview(reviewRecord);
        return new ReviewData(reviewCreateRequest.getRecipeId(),
                reviewCreateRequest.getReviewerId(),
                reviewCreateRequest.getRating(),
                reviewCreateRequest.getComment());
    }
}
