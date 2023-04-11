package com.kenzie.capstone.service;

import com.google.gson.Gson;
import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import com.kenzie.capstone.service.RecipeServiceLambda.util.MapperWrapper;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RecipeServiceTest {

    private RecipeDao recipeDao;
    private RecipeService recipeService;
    private MapperWrapper mapper;

    @BeforeAll
    void setup() {
        this.recipeDao = mock(RecipeDao.class);
        this.mapper = mock(MapperWrapper.class);
        this.recipeService = new RecipeService(recipeDao, mapper);
    }

    @Test
    void getAllRecipes_success() {
        String query = "test";
        String recipeInfo = "{testJson}";

        RecipeResponse response1 = new RecipeResponse();
        response1.setName("test");
        response1.setRecipeId("test");
        response1.setImage("test");
        response1.setInstructions("test");
        response1.setRecipeId("test");
        response1.setServings(2);
        response1.setReadyInMinutes(30);

        RecipeResponse response2 = new RecipeResponse();
        response2.setName("test");
        response2.setRecipeId("test");
        response2.setImage("test");
        response2.setInstructions("test");
        response2.setRecipeId("test");
        response2.setServings(4);
        response2.setReadyInMinutes(60);

        List<RecipeResponse> recipeResponses = new ArrayList<>();
        recipeResponses.add(response1);
        recipeResponses.add(response2);

        RecipeSearchApiResponse recipeSearchApiResponse = new RecipeSearchApiResponse(recipeResponses);

        List<RecipeSearchApiResponse> recipeSearchApiResponsesList = new ArrayList<>();
        recipeSearchApiResponsesList.add(recipeSearchApiResponse);

        GetAllRecipesApiResponse allRecipesApiResponse = new GetAllRecipesApiResponse();
        allRecipesApiResponse.setRecipes(recipeSearchApiResponsesList);

        when(recipeDao.getAllRecipes(query)).thenReturn(recipeInfo);
        when(mapper.recipeResponse(recipeInfo)).thenReturn(allRecipesApiResponse);
        when(recipeService.getRecipeInformation("test")).thenReturn(response1);

        List<RecipeResponse> result = recipeService.getAllRecipes(query);

        verify(recipeDao, times(1)).getAllRecipes(query);
        assertNotNull(result);
    }

    @Test
    void getRandomRecipe_Success(){
        String recipeJson = "{testJson}";

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId("test");
        recipe1.setName("test");
        recipe1.setInstructions("test");
        recipe1.setServings(6);
        recipe1.setReadyInMinutes(45);
        recipe1.setSourceUrl("test");
        recipe1.setImage("test");

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId("test");
        recipe2.setName("test");
        recipe2.setInstructions("test");
        recipe2.setServings(4);
        recipe2.setReadyInMinutes(20);
        recipe2.setSourceUrl("test");
        recipe2.setImage("test");

        RecipeResponse response = new RecipeResponse();
        response.setRecipeId("test");
        response.setServings(6);
        response.setName("test");
        response.setInstructions("test");
        response.setImage("test");
        response.setSourceUrl("test");

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        RandomRecipeResponse randomRecipeResponse = new RandomRecipeResponse(recipes);

        when(recipeDao.getRandomRecipe()).thenReturn(recipeJson);
        when(recipeDao.getRecipeInformation("test")).thenReturn(recipeJson);
        when(mapper.randomRecipeResponse(recipeJson)).thenReturn(randomRecipeResponse);
        when(recipeService.getRecipeInformation("test")).thenReturn(response);

        List<Recipe> result = recipeService.getRandomRecipe();

        verify(recipeDao, times(1)).getRandomRecipe();
        assertNotNull(result);
    }

    @Test
    void searchByNutrients(){

        String query = "minCarbs=5";
        String id = "17281";
        String id2 = "17282";
        String json = "[{test}]";

        when(recipeDao.searchByNutrients(query)).thenReturn(json);
        when(recipeService.getRecipeInformation(id)).thenReturn(new RecipeResponse(id, "name", "image", "instructions", 20, "url", 6));
        when(mapper.searchByNutrients(json)).thenReturn(Arrays.asList(new RecipeResponse(id, "name", "image", "instructions", 20, "url", 6), new RecipeResponse(id2, "name2", "image", "instructions", 20, "url", 6)));

        List<RecipeResponse> result = recipeService.searchByNutrients(query);

        verify(recipeDao, times(1)).searchByNutrients(query);
        verify(recipeDao, times(2)).getRecipeInformation(id);
        verify(mapper, times(1)).searchByNutrients(json);
        assertNotNull(result);
    }

    @Test
    void getRecipeInformation() {
        String recipeId = "20183";
        String jsonResponse = "{test}";

        when(recipeDao.getRecipeInformation(recipeId)).thenReturn(jsonResponse);
        when(mapper.getRecipeInformation(jsonResponse)).thenReturn(new RecipeResponse(recipeId, "name", "image",
                "instructions", 20, "url", 6));

        RecipeResponse result = recipeService.getRecipeInformation(recipeId);

        verify(recipeDao, times(1)).getRecipeInformation(recipeId);
        verify(mapper, times(1)).getRecipeInformation(jsonResponse);
        assertEquals(recipeId, result.getRecipeId());
        assertNotNull(result);
    }

    @Test
    void searchByIngredients() {
        String query = "apple,flour,sugar";
        String recipeId = "20183";
        String recipeId2 = "20182";
        String jsonResponse = "{test}";

        when(recipeDao.searchByIngredients(query)).thenReturn(jsonResponse);
        when(mapper.searchByIngredients(jsonResponse)).thenReturn(Arrays.asList(new RecipeResponse(recipeId, "name", "image", "instructions", 20, "url", 6), new RecipeResponse(recipeId2, "name2", "image", "instructions", 20, "url", 6)));

        List<RecipeResponse> result = recipeService.searchByIngredients(query);

        verify(recipeDao, times(1)).searchByIngredients(query);
        verify(mapper, times(1)).searchByIngredients(jsonResponse);
        assertNotNull(result);
    }
}
