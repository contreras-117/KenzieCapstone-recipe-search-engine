package com.kenzie.appserver.service;

import com.kenzie.appserver.controller.model.RecipeResponse;
import dao.RecipeDao;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RecipeService {
    RecipeDao recipeDao;

    public RecipeService(RecipeDao recipeDao){
        this.recipeDao = recipeDao;
    }
    public List<RecipeResponse> getAllRecipes(){
        return null;
    }

    public RecipeResponse getRandomRecipe() throws IOException, InterruptedException {
//        need to implement logic to check that recipe is unique to user
        return recipeDao.getRandomRecipe();
    }

}
