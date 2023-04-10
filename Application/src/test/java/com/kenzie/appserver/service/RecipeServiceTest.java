package com.kenzie.appserver.service;

import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient.RecipeLambdaServiceClient;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecipeServiceTest  {
    private ChefMateUserService chefMateUserService;
    private ReviewLambdaServiceClient reviewLambdaServiceClient;
    private ChefMateUserRepository chefMateUserRepository;
    private RecipeLambdaServiceClient recipeLambdaServiceClient;
    private CacheStore cache;

    @BeforeEach
    void setup() {
        recipeLambdaServiceClient = mock(RecipeLambdaServiceClient.class);
        cache = mock(CacheStore.class);
        chefMateUserService = new ChefMateUserService(chefMateUserRepository, reviewLambdaServiceClient, recipeLambdaServiceClient, cache);

    }
    /** ------------------------------------------------------------------------
     *  recipeService.searchByNutrients
     *  ------------------------------------------------------------------------ **/

    @Test
    void searchByNutrients() {
        String query = "minCarbs=5";

        List<RecipeResponse> recipeResponses = new ArrayList<>();
        recipeResponses.add(new RecipeResponse());
        recipeResponses.add(new RecipeResponse());

        when(recipeLambdaServiceClient.getSearchByNutrients(query)).thenReturn(recipeResponses);

        List<RecipeResponse> results = chefMateUserService.searchByNutrients(query);

        Assertions.assertNotNull(results, "The results are not null");
        Assertions.assertEquals(recipeResponses.size(), results.size());
    }

    @Test
    void getAllRecipes(){
        String query = "Chicken";
        List<RecipeResponse> expected = new ArrayList<>();
        expected.add(new RecipeResponse());
        expected.add(new RecipeResponse());

        when(recipeLambdaServiceClient.getAllRecipes(query)).thenReturn(expected);

        List<RecipeResponse> actual = chefMateUserService.getAllRecipes(query);

        Assertions.assertNotNull(actual, "The recipes should not be null!");
        Assertions.assertEquals(expected.size(), actual.size());
    }

    @Test
    void getRandomRecipe() {
        List<Recipe> expected = new ArrayList<>();
        expected.add(new Recipe());
        expected.add(new Recipe());

        when(recipeLambdaServiceClient.getRandomRecipe()).thenReturn(expected);

        List<Recipe> actual = chefMateUserService.getRandomRecipe();

        Assertions.assertNotNull(actual, "The list of recipes should not be null!");
        Assertions.assertEquals(actual.size(), expected.size());
    }

}
