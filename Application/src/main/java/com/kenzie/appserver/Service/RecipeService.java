package com.kenzie.appserver.Service;

import com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient.RecipeLambdaServiceClient;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecipeService {
    private RecipeLambdaServiceClient recipeLambdaServiceClient;

    public RecipeService(RecipeLambdaServiceClient recipeLambdaServiceClient) {
        this.recipeLambdaServiceClient = recipeLambdaServiceClient;
    }

    public String getAllRecipes(String query){
        return recipeLambdaServiceClient.getAllRecipes(query);
    }

    public String getRandomRecipe(){
        return recipeLambdaServiceClient.getRandomRecipe();
    }

    public List<RecipeResponse> searchByNutrients(String query) {
        return recipeLambdaServiceClient.getSearchByNutrients(query);
    }

    // Old Implementation
    /*public List<RecipeResponse> searchByNutrients(Map<String, Object> parameters) {
        return recipeLambdaServiceClient.getSearchByNutrients(parameters);
    }*/

}
