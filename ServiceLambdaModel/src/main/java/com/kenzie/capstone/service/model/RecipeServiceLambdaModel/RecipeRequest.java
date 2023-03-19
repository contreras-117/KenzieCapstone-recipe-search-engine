package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

public class RecipeRequest {
    private String recipeId;
    private String name;
    private String instructions;

    public RecipeRequest(String recipeId, String name, String instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.instructions = instructions;
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
