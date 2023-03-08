package com.kenzie.appserver.service;

import com.kenzie.appserver.client.SpoonacularClient;
import com.kenzie.appserver.controller.model.RecipeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class RecipeServiceTest {

    private RecipeService recipeService;
    private SpoonacularClient spoonacularClient;

    @BeforeEach
    void setup(){
        spoonacularClient = mock(SpoonacularClient.class);
        recipeService = new RecipeService(spoonacularClient);
    }

    @Test
    void getRandomRecipe_returnsRandomRecipe() throws IOException, InterruptedException {
        RecipeResponse response = recipeService.getRandomRecipe();


    }
}
