package com.kenzie.capstone.service;

import com.kenzie.capstone.service.ReviewServiceLambda.ReviewService;
import com.kenzie.capstone.service.ReviewServiceLambda.dao.ReviewDao;

import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ReviewServiceTest {

    /** ------------------------------------------------------------------------
     *  expenseService.getExpenseById
     *  ------------------------------------------------------------------------ **/

    private ReviewDao reviewDao;
    private ReviewService reviewService;

    @BeforeAll
    void setup() {
        this.reviewDao = mock(ReviewDao.class);
        this.reviewService = new ReviewService(reviewDao);
    }

    @Test
    void getReviewsTest() {
        ReviewRecord record1 = new ReviewRecord();
        record1.setRecipeId("test id");
        record1.setReviewerId("test userId");
        record1.setComment("test comment");
        record1.setRating(4.0);

        ReviewRecord record2 = new ReviewRecord();
        record2.setRecipeId("test id");
        record2.setReviewerId("test userId");
        record2.setComment("test comment");
        record2.setRating(4.0);

        List<ReviewRecord> recordList = new ArrayList<>();
        recordList.add(record1);
        recordList.add(record2);

        when(reviewDao.getRecipeReviews("test id")).thenReturn(recordList);
        List<Review> reviewList = reviewService.getRecipeReviews("test id");

        for(Review review : reviewList) {
            Assertions.assertEquals(review.getRecipeId(), "test id");
            Assertions.assertEquals(review.getReviewerId(), "test userId");
            Assertions.assertEquals(review.getRating(), 4.0);
        }
    }

    @Test
    void addReviewTest() {
        ReviewCreateRequest createRequest = new ReviewCreateRequest();
        createRequest.setRating(4.0);
        createRequest.setReviewerId("test user");
        createRequest.setRecipeId("test recipeId");
        createRequest.setComment("test comment");


        reviewService.addReview(createRequest);
        verify(reviewDao, times(1)).addReview(any());
    }
    
}