package com.kenzie.appserver.controller;

import com.kenzie.appserver.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/food/search")
    public String getAllRecipes(String query){
        return recipeService.getAllRecipes(query);
    }

    @GetMapping("/random")
    public String getRandomRecipe(){
        return recipeService.getRandomRecipe();
    }
}
