package com.kenzie.capstone.service.model;

public class RecipeResponse {

    private String recipeId;
    private String name;
    private String instructions;

    public RecipeResponse(String recipeId, String name, String instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.instructions = instructions;
    }

    public RecipeResponse() {
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
