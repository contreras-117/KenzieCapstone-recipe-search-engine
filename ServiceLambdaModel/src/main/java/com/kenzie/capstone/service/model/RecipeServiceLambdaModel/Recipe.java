package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import java.util.Objects;

public class Recipe {
    private String recipeId;
    private String name;
    private String image;
    private String instructions;

    public Recipe(String recipeId, String name, String image, String instructions) {
        this.recipeId = recipeId;
        this.name = name;
        this.image = image;
        this.instructions = instructions;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(recipeId, recipe.recipeId) && Objects.equals(name, recipe.name) && Objects.equals(instructions, recipe.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, name, instructions);
    }
}

