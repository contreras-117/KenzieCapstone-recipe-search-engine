package com.kenzie.capstone.service.RecipeServiceLambda.dao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RecipeDao {
    private static final String SPOONACULAR_API_URL = "https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";
    private static final String API_KEY = "66ada6da0bmshd09fe8ebb84e544p1f2c13jsn12e801189520";
    private static final String API_HEADER = "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com";

    public String getAllRecipes(String query) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPOONACULAR_API_URL + "/food/search?query=" + query))
                .header("Accept", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HEADER)
                .GET()
                .build();

        try{
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e){
            return e.getMessage();
        }
    }

    public String getRandomRecipe() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPOONACULAR_API_URL + "/random" + API_KEY))
                .header("Accept", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HEADER)
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                throw new IOException(String.format("GET request failed: %d status code received", statusCode));
            }
        } catch (IOException | InterruptedException e){
            return e.getMessage();
        }
    }

    public String searchByNutrients(String query) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPOONACULAR_API_URL + "/recipes/findByNutrients?" + query))
                .header("Accept", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HEADER)
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET by nutrients failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getRecipeInformation(String recipeId) {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPOONACULAR_API_URL + "/recipes/" + recipeId + "/information"))
                .header("Accept", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HEADER)
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET information by recipe ID failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

}
