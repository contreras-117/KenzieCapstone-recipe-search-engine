package com.kenzie.capstone.service.converter;

import com.kenzie.capstone.service.converter.converter.RecipeConverter;
import com.kenzie.capstone.service.converter.dao.RecipeDao;
import com.kenzie.capstone.service.dao.ReviewDao;
import com.kenzie.capstone.service.model.ReviewRecord;
import com.kenzie.capstone.service.model.ReviewRequest;
import com.kenzie.capstone.service.model.ReviewResponse;

import javax.inject.Inject;

public class RecipeService {

    private RecipeDao recipeDao;

    @Inject
    public RecipeService(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public String getAllRecipes(String query) {
        //will finish implementing
        String result = recipeDao.getAllRecipes(query);

        return result;
    }

    public String getRandomRecipe(){
        //will finish implementing
        String result = recipeDao.getRandomRecipe();

        return result;
    }
}
