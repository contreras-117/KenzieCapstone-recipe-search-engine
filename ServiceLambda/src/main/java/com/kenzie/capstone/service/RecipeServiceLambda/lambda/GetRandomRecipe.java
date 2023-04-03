package com.kenzie.capstone.service.RecipeServiceLambda.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dependency.DaggerServiceComponent;
import com.kenzie.capstone.service.RecipeServiceLambda.dependency.ServiceComponent;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRandomRecipe implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
        static final Logger log = LogManager.getLogger();

        @Override
        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            log.info(gson.toJson(input));

            ServiceComponent serviceComponent = DaggerServiceComponent.create();
            RecipeService lambdaService = serviceComponent.provideLambdaService();
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");

            APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
                    .withHeaders(headers);

            try {
                List<Recipe> recipes = lambdaService.getRandomRecipe();
                String output = gson.toJson(recipes);

                return response
                        .withStatusCode(200)
                        .withBody(output);

            } catch (Exception e) {
                return response
                        .withStatusCode(400)
                        .withBody(gson.toJson(e.getMessage()));
            }
        }
}
