package com.kenzie.appserver.service;

import com.kenzie.appserver.Service.ChefMateUserService;
import com.kenzie.appserver.repositories.ChefMateUserRepository;
import com.kenzie.capstone.service.client.RecipeServiceLambdaJavaClient.RecipeLambdaServiceClient;
import com.kenzie.capstone.service.client.ReviewServiceLambdaJavaClient.ReviewLambdaServiceClient;
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

    @BeforeEach
    void setup() {
        recipeLambdaServiceClient = mock(RecipeLambdaServiceClient.class);
        chefMateUserService = new ChefMateUserService(chefMateUserRepository, reviewLambdaServiceClient, recipeLambdaServiceClient);

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

}
