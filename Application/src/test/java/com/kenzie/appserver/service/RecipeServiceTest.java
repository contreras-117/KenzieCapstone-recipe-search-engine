package com.kenzie.appserver.service;

import dao.RecipeDao;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RecipeServiceTest {

    private RecipeService recipeService;
    private RecipeDao recipeDao;

    @BeforeEach
    void setup(){
        recipeDao = mock(RecipeDao.class);
        recipeService = new RecipeService(recipeDao);
    }

    @Test
    void getRandomRecipe_returnsRandomRecipe() throws IOException, InterruptedException {

    }
}
