package com.kenzie.capstone.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Arrays;
import java.util.Objects;

@DynamoDBTable(tableName = "Recipe")
public class RecipeRecord {

    private String recipeId;
    private String creatorID;
    private String[] recipeTags;

    private String[] ingredients;
    private String[] directions;

    @DynamoDBHashKey(attributeName = "recipeId")
    public String getRecipeId() {
        return recipeId;
    }

    @DynamoDBAttribute(attributeName = "creatorId")
    public String getCreatorID() {
        return creatorID;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public void setCreatorID(String creatorID) {
        this.creatorID = creatorID;
    }
    @DynamoDBAttribute(attributeName = "recipeTags")
    public String[] getRecipeTags() {
        return recipeTags;
    }

    public void setRecipeTags(String[] recipeTags) {
        this.recipeTags = recipeTags;
    }
    @DynamoDBAttribute(attributeName = "ingredients")
    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }
    @DynamoDBAttribute(attributeName = "directions")
    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }
}
