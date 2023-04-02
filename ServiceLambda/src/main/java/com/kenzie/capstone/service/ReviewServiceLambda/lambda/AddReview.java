package com.kenzie.capstone.service.ReviewServiceLambda.lambda;

import com.kenzie.capstone.service.ReviewServiceLambda.ReviewService;
import com.kenzie.capstone.service.ReviewServiceLambda.dependency.ServiceComponent;
import com.kenzie.capstone.service.ReviewServiceLambda.converter.JsonStringToReviewConverter;
import com.kenzie.capstone.service.ReviewServiceLambda.dependency.DaggerServiceComponent;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewCreateRequest;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewData;
import com.kenzie.capstone.service.model.ReviewServiceLambdaModel.ReviewResponse;
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
            ReviewData reviewData = reviewLambdaService.addReview(reviewCreateRequest);

            return response
                    .withStatusCode(200)
                    .withBody(gson.toJson(reviewData));

        } catch (Exception e) {
            return response
                    .withStatusCode(400)
                    .withBody(gson.toJson(e.getMessage()));
        }
    }
}
