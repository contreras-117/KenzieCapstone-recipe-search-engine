package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.constraints.NotEmpty;

public class CreateChefMateUserRequest {
    @NotEmpty
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("userPreferences")
    private Optional<List<String>>userPreferences;

    @JsonProperty("recipesTried")
    private Optional<Set<String>> recipesTried;

    @JsonProperty("ingredients")
    private Optional<Set<String>> ingredients;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Optional<List<String>> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(Optional<List<String>> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public Optional<Set<String>> getRecipesTried() {
        return recipesTried;
    }

    public void setRecipesTried(Optional<Set<String>> recipesTried) {
        this.recipesTried = recipesTried;
    }

    public Optional<Set<String>> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Optional<Set<String>> ingredients) {
        this.ingredients = ingredients;
    }
}
