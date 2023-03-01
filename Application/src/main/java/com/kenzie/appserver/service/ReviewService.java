package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.ReviewRepository;
import com.kenzie.appserver.service.model.Review;

import com.kenzie.capstone.service.client.LambdaServiceClient;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private ReviewRepository exampleRepository;
    private LambdaServiceClient lambdaServiceClient;

    public ReviewService(ReviewRepository reviewRepository, LambdaServiceClient lambdaServiceClient) {
        this.exampleRepository = reviewRepository;
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

    public Review addNewExample(String name) {
//        // Example sending data to the lambda
//        ExampleData dataFromLambda = lambdaServiceClient.setExampleData(name);
//
//        // Example sending data to the local repository
//        ReviewRecord exampleRecord = new ReviewRecord();
//        exampleRecord.setId(dataFromLambda.getId());
//        exampleRecord.setName(dataFromLambda.getData());
//        exampleRepository.save(exampleRecord);
//
//        Example example = new Example(dataFromLambda.getId(), name);
//        return example;
        return null;
    }
}
