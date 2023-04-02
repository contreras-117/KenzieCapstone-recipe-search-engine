package com.kenzie.capstone.service.RecipeServiceLambda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeRequest;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;

import javax.inject.Inject;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecipeService {

    private RecipeDao recipeDao;

    @Inject
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public String getAllRecipes(String query) {
        //will finish implementing
        String result = recipeDao.getAllRecipes(query);

        return result;
    }

    public String getRandomRecipe(){
        return recipeDao.getRandomRecipe();
    }

    public List<RecipeResponse> searchByNutrients(String query) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String recipesByNutrientsJson = recipeDao.searchByNutrients(query);

        List<RecipeResponse> recipes = gson.fromJson(recipesByNutrientsJson, new TypeToken<List<RecipeResponse>>() { }.getType());

        for (RecipeResponse recipe : recipes) {
            RecipeResponse recipeInformation = getRecipeInformation(recipe.getRecipeId());

            recipe.setInstructions(recipeInformation.getInstructions());
            recipe.setServings(recipeInformation.getServings());
            recipe.setReadyInMinutes(recipeInformation.getReadyInMinutes());
            recipe.setSourceUrl(recipeInformation.getSourceUrl());
        }

        return recipes;
    }

    public RecipeResponse getRecipeInformation(String recipeId) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String jsonRecipeInformation = recipeDao.getRecipeInformation(recipeId);

        return gson.fromJson(jsonRecipeInformation, new TypeToken<RecipeResponse>(){}.getType());
    }

/*    public static void main(String[] args) {
        RecipeDao recipeDao1 = new RecipeDao();
        RecipeService recipeService = new RecipeService(recipeDao1);
        RecipeResponse recipeResponse = recipeService.getRecipeInformation("20183");
       // System.out.println(recipeResponse.getRecipeId());

        List<RecipeResponse> recipeResponses = recipeService.searchByNutrients("minCarbs=10");

        int counter = 1;

        for (RecipeResponse response : recipeResponses) {
            System.out.println("Recipe #" + counter++);
            System.out.println(response.getRecipeId());
            System.out.println(response.getName());
            System.out.println(response.getImage());
            System.out.println(response.getSourceUrl());
            System.out.println(response.getServings());
            System.out.println(response.getInstructions());
            System.out.println(response.getReadyInMinutes());
            System.out.println();
        }
    }*/

}
