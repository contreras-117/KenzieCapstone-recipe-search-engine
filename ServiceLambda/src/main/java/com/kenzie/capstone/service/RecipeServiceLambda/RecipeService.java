package com.kenzie.capstone.service.RecipeServiceLambda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import com.kenzie.capstone.service.RecipeServiceLambda.util.MapperWrapper;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.*;

import javax.inject.Inject;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecipeService {

    private RecipeDao recipeDao;
    private MapperWrapper mapper;

    @Inject
    public RecipeService(RecipeDao recipeDao, MapperWrapper mapper) {
        this.recipeDao = recipeDao;
        this.mapper = mapper;
    }

    public List<RecipeResponse> getAllRecipes(String query) {
        String recipeInfo = recipeDao.getAllRecipes(query);
        GetAllRecipesApiResponse recipeResponse = mapper.recipeResponse(recipeInfo);
        List<RecipeResponse> recipes = new ArrayList<>();

        for (RecipeResponse recipe : recipeResponse.getRecipes().get(0).getRecipes()) {
            RecipeResponse recipeInformation = getRecipeInformation(recipe.getRecipeId());
            recipe.setName(recipeInformation.getName());
            recipe.setInstructions(recipeInformation.getInstructions());
            recipe.setServings(recipeInformation.getServings());
            recipe.setReadyInMinutes(recipeInformation.getReadyInMinutes());
            recipe.setSourceUrl(recipeInformation.getSourceUrl());
            recipe.setImage(recipeInformation.getImage());
            recipes.add(recipe);
        }

        return recipes;
    }

    public List<Recipe> getRandomRecipe(){
        String recipeInfo = recipeDao.getRandomRecipe();

        RandomRecipeResponse randomRecipesResponse = mapper.randomRecipeResponse(recipeInfo);

        List<Recipe> recipes = new ArrayList<>();

        for (Recipe recipe : randomRecipesResponse.getRecipes()){
            RecipeResponse recipeInformation = getRecipeInformation(recipe.getRecipeId());
            recipe.setInstructions(recipeInformation.getInstructions());
            recipe.setImage(recipeInformation.getImage());
            recipe.setServings(recipeInformation.getServings());
            recipe.setName(recipeInformation.getName());
            recipe.setSourceUrl(recipeInformation.getSourceUrl());
            recipe.setReadyInMinutes(recipeInformation.getReadyInMinutes());
            recipes.add(recipe);
        }
        return recipes;
    }

    public List<RecipeResponse> searchByNutrients(String query) {

        String recipesByNutrientsJson = recipeDao.searchByNutrients(query);

        List<RecipeResponse> recipes = mapper.searchByNutrients(recipesByNutrientsJson);

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

        String jsonRecipeInformation = recipeDao.getRecipeInformation(recipeId);

        return mapper.getRecipeInformation(jsonRecipeInformation);
    }

    public List<RecipeResponse> searchByIngredients(String query) {

        String jsonRecipeResponse = recipeDao.searchByIngredients(query);

        List<RecipeResponse> recipes = mapper.searchByIngredients(jsonRecipeResponse);

        for (RecipeResponse recipe : recipes) {
            RecipeResponse recipeInformation = getRecipeInformation(recipe.getRecipeId());

            recipe.setInstructions(recipeInformation.getInstructions());
            recipe.setServings(recipeInformation.getServings());
            recipe.setReadyInMinutes(recipeInformation.getReadyInMinutes());
            recipe.setSourceUrl(recipeInformation.getSourceUrl());
        }

        return recipes;
    }

//    public static void main(String[] args) {
//        RecipeDao recipeDao1 = new RecipeDao();
//        RecipeService recipeService = new RecipeService(recipeDao1);
//        RecipeResponse recipeResponse = recipeService.getRecipeInformation("20183");
//       // System.out.println(recipeResponse.getRecipeId());
//
/*        List<RecipeResponse> recipeResponses = recipeService.searchByIngredients("bannans,flour,sugar&number=2");

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
        }*/
//
//        List<RecipeResponse> recipeResponse1 = recipeService.getAllRecipes("Chicken");
//        int counter = 1;
//        for(RecipeResponse recipe : recipeResponse1) {
//            System.out.println("Recipe #" + counter++);
//            System.out.println(recipe.getName());
//            System.out.println(recipe.getInstructions());
//            System.out.println(recipe.getServings());
//        }

//        List<Recipe> recipes = recipeService.getRandomRecipe();
//        for (Recipe recipe : recipes) {
//            System.out.println("Recipe #" + counter++);
//            System.out.println(recipe.getName());
//            System.out.println(recipe.getServings());
//            System.out.println(recipe.getInstructions());
//        }
//    }

}
