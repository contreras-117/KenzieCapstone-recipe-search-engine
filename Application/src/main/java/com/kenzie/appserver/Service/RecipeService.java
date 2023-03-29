package com.kenzie.appserver.service;

import com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient.RecipeLambdaServiceClient;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private RecipeLambdaServiceClient recipeLambdaServiceClient = new RecipeLambdaServiceClient();

    public String getAllRecipes(String query){
        return recipeLambdaServiceClient.getAllRecipes(query);
    }

    public String getRandomRecipe(){
        return recipeLambdaServiceClient.getRandomRecipe();
    }

}
