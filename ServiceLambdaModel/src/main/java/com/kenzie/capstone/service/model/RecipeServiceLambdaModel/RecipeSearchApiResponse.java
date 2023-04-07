package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeSearchApiResponse implements Serializable {
    @SerializedName("results")
    @JsonProperty("results")
    List<RecipeResponse> recipes;

    public RecipeSearchApiResponse(List<RecipeResponse> recipes) {
        this.recipes = recipes;
    }

    public List<RecipeResponse> getRecipes() {
        return recipes;
    }
}
