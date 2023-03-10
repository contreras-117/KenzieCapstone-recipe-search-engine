package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChefMateUserResponse {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userPreferences")
    private List<String> userPreferences;

    @JsonProperty("recipesTried")
    private Set<String> recipesTried;

    @JsonProperty("ingredients")
    private Set<String> ingredients;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(List<String> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public Set<String> getRecipesTried() {
        return recipesTried;
    }

    public void setRecipesTried(Set<String> recipesTried) {
        this.recipesTried = recipesTried;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
