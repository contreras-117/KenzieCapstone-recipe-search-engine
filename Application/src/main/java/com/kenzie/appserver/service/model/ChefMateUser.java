package com.kenzie.appserver.service.model;

import java.util.List;
import java.util.Set;

public class ChefMateUser {
    private final String userId;
    private List<String> userPreferences;
    private Set<String> recipesTried;
    private Set<String> ingredients;

    public ChefMateUser(String userId, List<String> userPreferences, Set<String> recipesTried, Set<String> ingredients) {
        this.userId = userId;
        this.userPreferences = userPreferences;
        this.recipesTried = recipesTried;
        this.ingredients = ingredients;
    }
}
