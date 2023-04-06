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
    private static final String GET_RANDOM_RECIPE_ENDPOINT = "recipes/random";

    private static final String GET_SEARCH_BY_NUTRIENTS_ENDPOINT = "recipes/food/search/nutrients/{query}";
    private static final String GET_SEARCH_BY_INGREDIENTS_ENDPOINT = "recipes/food/search/ingredients/{query}";

    private ObjectMapper mapper;

    public RecipeLambdaServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public List<RecipeResponse> getAllRecipes(String query){
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_ALL_FOOD_ENDPOINT) + query;

        List<RecipeResponse> recipes;

        try {
            recipes = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        return recipes;
    }

    public List<Recipe> getRandomRecipe(){
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_RANDOM_RECIPE_ENDPOINT);

        List<Recipe> recipes;
        try {
            recipes = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        return recipes;
    }

    public List<RecipeResponse> getSearchByNutrients(String query) {
        EndpointUtility endpointUtility = new EndpointUtility();

        String response = endpointUtility.getEndpoint(GET_SEARCH_BY_NUTRIENTS_ENDPOINT.replace("{query}", query));

        List<RecipeResponse> recipes;

        try {
            recipes = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        return recipes;
    }

    public List<RecipeResponse> getSearchByIngredients(String query) {
        EndpointUtility endpointUtility = new EndpointUtility();

        String response = endpointUtility.getEndpoint(GET_SEARCH_BY_INGREDIENTS_ENDPOINT.replace("{query}", query));

        List<RecipeResponse> recipes;

        try {
            recipes = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }

        return recipes;
    }
}
