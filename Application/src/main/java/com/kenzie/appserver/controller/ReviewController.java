package com.kenzie.appserver.controller;

import com.kenzie.appserver.Service.ReviewService;
import com.kenzie.appserver.Service.model.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
public class ReviewController {
        private ReviewService reviewService;

        ReviewController(ReviewService reviewService) {
            this.reviewService = reviewService;
        }

//        @GetMapping("/{id}")
//        public ResponseEntity<ReviewResponse> get(@PathVariable("id") String id) {
//
//            Review review = reviewService.findById(id);
//            if (review == null) {
//                return ResponseEntity.notFound().build();
//            }
//
//            ReviewResponse reviewResponse = new ReviewResponse();
////            exampleResponse.setId(example.getId());
////            exampleResponse.setName(example.getName());
////            return ResponseEntity.ok(exampleResponse);
//            return null;
//        }

        @PostMapping
        public ResponseEntity<ReviewResponse> addNewExample(@RequestBody ReviewCreateRequest reviewCreateRequest) {
            Review review = reviewService.addNewReview(reviewCreateRequest);

            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setReviewerId(reviewCreateRequest.getReviewerId());
            reviewResponse.setComment(reviewCreateRequest.getComment());
            reviewResponse.setRating(reviewCreateRequest.getRating());
            reviewResponse.setRecipeId(reviewCreateRequest.getRecipeId());

            return ResponseEntity.ok(reviewResponse);
        }
}
