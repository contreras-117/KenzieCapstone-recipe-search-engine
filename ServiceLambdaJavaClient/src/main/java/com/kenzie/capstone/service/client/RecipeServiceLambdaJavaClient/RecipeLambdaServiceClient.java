package com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.client.EndpointUtility;


public class RecipeLambdaServiceClient {

    private static final String GET_ALL_FOOD_ENDPOINT = "/food/search?query=";
    private static final String GET_RANDOM_RECIPE_ENDPOINT = "/random";

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
}
