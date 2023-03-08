package com.kenzie.capstone.service;

import com.kenzie.capstone.service.converter.ReviewConverter;
import com.kenzie.capstone.service.dao.ReviewDao;
import com.kenzie.capstone.service.model.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewRequest;
import com.kenzie.capstone.service.model.ReviewResponse;

import javax.inject.Inject;

public class ReviewService {

    private ReviewDao reviewDao;

    @Inject
    public ReviewService(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

//    public ExampleData getExampleData(String id) {
//        List<RecipeRecord> records = exampleDao.getExampleData(id);
//        if (records.size() > 0) {
//            return new ExampleData(records.get(0).getId(), records.get(0).getData());
//        }
//        return null;
//    }

//    public ExampleData setExampleData(String data) {
//        String id = UUID.randomUUID().toString();
//        RecipeRecord record = exampleDao.setExampleData(id, data);
//        return new ExampleData(id, data);
//    }
    public ReviewResponse addReview(ReviewRequest review) {
        //TODO implement all ID checks
//        if (review == null ||)
        ReviewRecord record = new ReviewRecord();
        record.setReviewerId(review.getReviewerId());
        record.setComment(review.getComment());
        record.setRating(review.getRating());
        record.setRecipeId(review.getRecipeId());

        reviewDao.addReview(record);
        return ReviewConverter.fromRecordToResponse(record);

    }
}
