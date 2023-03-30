package com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.client.ApiGatewayException;
import com.kenzie.capstone.service.client.EndpointUtility;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;


public class ReviewLambdaServiceClient {

    private static final String ADD_REVIEW_ENDPOINT = "review/add";
    private static final String GET_EXAMPLE_ENDPOINT = "review/get";

    private ObjectMapper mapper;

    public ReviewLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

//    public Review getExampleData(String id) {
//        EndpointUtility endpointUtility = new EndpointUtility();
//        String response = endpointUtility.getEndpoint(GET_EXAMPLE_ENDPOINT.replace("{id}", id));
//        Review review;
//        try {
//            review = mapper.readValue(response, Review.class);
//        } catch (Exception e) {
//            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//        return review;
//    }

    public ReviewResponse addReview(ReviewCreateRequest reviewCreateRequest) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String request;
        try {
            request = mapper.writeValueAsString(reviewCreateRequest);
        } catch(JsonProcessingException e) {
            throw new ApiGatewayException("Unable to serialize request: " + e);
        }
        String response = endpointUtility.postEndpoint(ADD_REVIEW_ENDPOINT, request);
        ReviewResponse reviewResponse;
        try {
            reviewResponse = mapper.readValue(response, ReviewResponse.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return reviewResponse;
    }
}
