package com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.client.ApiGatewayException;
import com.kenzie.capstone.service.client.EndpointUtility;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.Review;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;

import java.util.List;


public class ReviewLambdaServiceClient {

    private static final String ADD_REVIEW_ENDPOINT = "review/add";
    private static final String GET_REVIEW_ENDPOINT = "review/list/{recipeId}";

    private ObjectMapper mapper;

    public ReviewLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public List<Review> getRecipeReviews(String recipeId) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_REVIEW_ENDPOINT.replace("{recipeId}", recipeId));
        List<Review> reviews;
        try {
            reviews = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return reviews;
    }

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
