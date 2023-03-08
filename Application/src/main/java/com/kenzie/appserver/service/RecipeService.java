package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.RecipeResponse;
import com.kenzie.appserver.client.SpoonacularClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeService {
    SpoonacularClient spoonacularClient;

    public RecipeService(SpoonacularClient spoonacularClient){
        this.spoonacularClient = spoonacularClient;
    }
    public List<RecipeResponse> getAllRecipes(){
        return null;
    }

    public RecipeResponse getRandomRecipe() throws IOException, InterruptedException {
//        need to implement logic to check that recipe is unique to user
        return spoonacularClient.getRandomRecipe();
    }

}
