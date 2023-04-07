package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetAllRecipesApiResponse implements Serializable {
    @SerializedName("searchResults")
    @JsonProperty("searchResults")
    List<RecipeSearchApiResponse> recipes;

    public List<RecipeSearchApiResponse> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeSearchApiResponse> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "GetAllRecipesApiResponse{" +
                "recipes=" + recipes +
                '}';
    }
}