package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RandomRecipeResponse implements Serializable {
    @SerializedName("recipes")
    List<Recipe> recipes;

    public RandomRecipeResponse(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
