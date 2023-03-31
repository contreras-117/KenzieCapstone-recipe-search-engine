package com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.client.ApiGatewayException;
import com.kenzie.capstone.service.client.EndpointUtility;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;

import java.util.Collections;
import java.util.List;
import java.util.Map;


public class RecipeLambdaServiceClient {

    private static final String GET_ALL_FOOD_ENDPOINT = "/food/search?query=";
    private static final String GET_RANDOM_RECIPE_ENDPOINT = "/random";

    private static final String GET_SEARCH_BY_NUTRIENTS_ENDPOINT = "/recipes/food/search/nutrients/{query}";

    private ObjectMapper mapper;

    public RecipeLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public String getAllRecipes(String query){
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_ALL_FOOD_ENDPOINT)  + query;

        return response;
    }

    public String getRandomRecipe(){
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_RANDOM_RECIPE_ENDPOINT);

        return response;
    }

    public List<RecipeResponse> getSearchByNutrients(Map<String, Object> parameters) {
        EndpointUtility endpointUtility = new EndpointUtility();

        StringBuilder query = new StringBuilder();

        int counter = 0;

        for (Map.Entry<String, Object> parameter: parameters.entrySet()) {

            if (counter != parameters.entrySet().size()) {
                query.append(parameter.getKey()).append("=").append(parameter.getValue()).append("&");
            } else {
                query.append(parameter.getKey()).append("=").append(parameter.getValue());
            }

            counter++;
        }

        String response = endpointUtility.getEndpoint(GET_SEARCH_BY_NUTRIENTS_ENDPOINT.replace("{query}", query));

        List<RecipeResponse> recipes;

        try {
            recipes = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        return recipes;
    }
}
