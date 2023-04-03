package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetAllRecipesApiResponse implements Serializable {
    @SerializedName("searchResults")
    List<RecipeSearchApiResponse> recipes;

    public List<RecipeSearchApiResponse> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "GetAllRecipesApiResponse{" +
                "recipes=" + recipes +
                '}';
    }
}