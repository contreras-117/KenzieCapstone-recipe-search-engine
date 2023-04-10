package com.kenzie.capstone.service.RecipeServiceLambda.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.GetAllRecipesApiResponse;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RandomRecipeResponse;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import dagger.Module;

import javax.inject.Inject;
import java.util.List;

//@Module
public class MapperWrapper {
    private Gson gson;

    @Inject
    public MapperWrapper() {
        gson = new GsonBuilder().create();
    }

    public GetAllRecipesApiResponse recipeResponse(String recipeInfo) {
        return gson.fromJson(recipeInfo, new TypeToken<GetAllRecipesApiResponse>(){}.getType());
    }

    public RandomRecipeResponse randomRecipeResponse(String recipeInfo) {
        return gson.fromJson(recipeInfo, new TypeToken<RandomRecipeResponse>(){}.getType());
    }

    public List<RecipeResponse> searchByNutrients(String recipeInfo) {
        return gson.fromJson(recipeInfo, new TypeToken<List<RecipeResponse>>(){}.getType());
    }

    public RecipeResponse getRecipeInformation(String recipeInfo) {
        return gson.fromJson(recipeInfo, new TypeToken<RecipeResponse>(){}.getType());
    }

    public List<RecipeResponse> searchByIngredients(String recipeInfo) {
        return gson.fromJson(recipeInfo, new TypeToken<List<RecipeResponse>>() {}.getType());
    }
}
