package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

public class RecipeResponse {

    private String recipeId;
    private String name;
    private String image;
    private String instructions;

    public RecipeResponse(String recipeId, String name, String image, String instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.image = image;
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
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
