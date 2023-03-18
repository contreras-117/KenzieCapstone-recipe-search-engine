package com.kenzie.capstone.service.lambda;

import com.kenzie.capstone.service.ReviewService;
import com.kenzie.capstone.service.converter.JsonStringToReviewConverter;
import com.kenzie.capstone.service.dependency.ServiceComponent;
import com.kenzie.capstone.service.dependency.DaggerServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.model.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class AddReview implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    static final Logger log = LogManager.getLogger();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        JsonStringToReviewConverter converter = new JsonStringToReviewConverter();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        log.info(gson.toJson(input));

        ServiceComponent serviceComponent = DaggerServiceComponent.create();
        ReviewService reviewLambdaService = serviceComponent.provideLambdaService();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                .withHeaders(headers);

        String data = input.getBody();

        if (data == null || data.length() == 0) {
            return response
                    .withStatusCode(400)
                    .withBody("data is invalid");
        }

        try {
            ReviewCreateRequest reviewCreateRequest = converter.convert(input.getBody());
            ReviewResponse reviewResponse = reviewLambdaService.addReview(reviewCreateRequest);

            return response
                    .withStatusCode(200)
                    .withBody(gson.toJson(reviewResponse));

        } catch (Exception e) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.getMessage()));
        }
    }
}
