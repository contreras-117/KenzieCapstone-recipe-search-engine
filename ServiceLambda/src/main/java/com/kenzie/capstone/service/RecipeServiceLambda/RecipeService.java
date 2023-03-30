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

    public List<Recipe> searchByNutrients(String query) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        String recipesByNutrientsJson = recipeDao.searchByNutrients(query);

        List<RecipeResponse> recipeResponses = gson.fromJson(recipesByNutrientsJson, new TypeToken<List<RecipeResponse>>() { }.getType());

        for (RecipeResponse recipe : recipeResponses) {
            String recipeInformationJson = recipeDao.getRecipeInformation(recipe.getRecipeId());
            RecipeResponse recipeInformationRequest = gson.fromJson(recipeInformationJson, new TypeToken<RecipeResponse>(){}.getType());

            recipe.setInstructions(recipeInformationRequest.getInstructions());
        }

        List<Recipe> recipes = new ArrayList<>();

        for (RecipeResponse recipeResponse : recipeResponses) {
            recipes.add(new Recipe(recipeResponse.getRecipeId(), recipeResponse.getName(), recipeResponse.getImage(), recipeResponse.getInstructions()));
        }

        return recipes;
    }

}
