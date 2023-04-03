package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class RecipeSearchApiResponse implements Serializable {
    @SerializedName("results")
    List<RecipeResponse> recipes;

    public List<RecipeResponse> getRecipes() {
        return recipes;
    }
}
