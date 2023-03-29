package com.kenzie.appserver.Service;

import com.kenzie.appserver.Service.model.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository reviewRepository;
    private ReviewLambdaServiceClient lambdaServiceClient;

    public ReviewService(ReviewRepository exampleRepository, ReviewLambdaServiceClient lambdaServiceClient) {
        this.reviewRepository = exampleRepository;
        this.lambdaServiceClient = lambdaServiceClient;
    }

    public Review findById(String id) {
//
//        // Example getting data from the lambda
//        ExampleData dataFromLambda = lambdaServiceClient.getExampleData(id);
//
//        // Example getting data from the local repository
//        Example dataFromDynamo = exampleRepository
//                .findById(id)
//                .map(example -> new Example(example.getId(), example.getName()))
//                .orElse(null);
//
//        return dataFromDynamo;
        return null;
    }

    public Review addNewReview(ReviewCreateRequest request) {

        ReviewResponse dataFromLambda = lambdaServiceClient.addReview(request);

        ReviewRecord reviewRecord = new ReviewRecord();
        reviewRecord.setReviewerId(dataFromLambda.getReviewerId());
        reviewRecord.setRecipeId(dataFromLambda.getRecipeId());
        reviewRecord.setRating(dataFromLambda.getRating());
        reviewRecord.setComment(dataFromLambda.getComment());
        reviewRepository.save(reviewRecord);

        return new Review(dataFromLambda.getRecipeId(), dataFromLambda.getReviewerId(), dataFromLambda.getRating(), dataFromLambda.getComment());
    }
}
