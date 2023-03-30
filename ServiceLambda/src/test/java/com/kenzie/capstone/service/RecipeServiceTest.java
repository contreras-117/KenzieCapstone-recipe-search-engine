package com.kenzie.capstone.service;

import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeServiceTest {

    private RecipeDao recipeDao;
    private RecipeService recipeService;

    @BeforeAll
    void setup() {
        this.recipeDao = mock(RecipeDao.class);
        this.recipeService = new RecipeService(recipeDao);
    }

    @Test
    void getAllRecipes_success(){
        ArgumentCaptor<String> queryCaptor = ArgumentCaptor.forClass(String.class);

        String query = "test";

        recipeService.getAllRecipes(query);

        verify(recipeDao, times(1)).getAllRecipes(queryCaptor.capture());
        assertEquals(query, queryCaptor.getValue());
    }

    @Test
    void getRandomRecipe_Success(){
        when(recipeDao.getRandomRecipe()).thenReturn("Random recipe");

        String result = recipeService.getRandomRecipe();

        verify(recipeDao, times(1)).getRandomRecipe();
        assertNotNull(result);
    }
}
