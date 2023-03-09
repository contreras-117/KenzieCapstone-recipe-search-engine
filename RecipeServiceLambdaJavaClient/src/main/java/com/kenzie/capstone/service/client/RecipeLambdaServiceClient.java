package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.ReviewRequest;
import com.kenzie.capstone.service.model.ReviewResponse;


public class RecipeLambdaServiceClient {

    private static final String ADD_REVIEW_ENDPOINT = "review/add";
    private static final String SET_EXAMPLE_ENDPOINT = "example";

    private ObjectMapper mapper;

    public RecipeLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

//    public Review getExampleData(String id) {
//        com.kenzie.capstone.service.client.EndpointUtility endpointUtility = new com.kenzie.capstone.service.client.EndpointUtility();
//        String response = endpointUtility.getEndpoint(GET_EXAMPLE_ENDPOINT.replace("{id}", id));
//        Review review;
//        try {
//            review = mapper.readValue(response, Review.class);
//        } catch (Exception e) {
//            throw new com.kenzie.capstone.service.client.ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//        return review;
//    }

    public ReviewResponse addReview(ReviewRequest reviewRequest) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String request;
        try {
            request = mapper.writeValueAsString(reviewRequest);
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
