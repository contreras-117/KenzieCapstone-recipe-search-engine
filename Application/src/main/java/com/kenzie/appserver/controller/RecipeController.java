package com.kenzie.appserver.controller;

import com.kenzie.appserver.Service.RecipeService;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeRequest;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

   @GetMapping("/food/search/nutrients/{query}")
    public ResponseEntity<List<RecipeResponse>> searchByNutrients(@PathVariable("query") String query) {

        return ResponseEntity.ok(recipeService.searchByNutrients(query));
    }

    // Old implementation
/*    @GetMapping("/food/search/nutrients/{query}")
    public ResponseEntity<List<RecipeResponse>> searchByNutrients(@PathVariable("query") String json) {

        *//*GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();*//*

        GsonJsonParser gsonJsonParser = new GsonJsonParser();

        Map<String, Object> parameters = gsonJsonParser.parseMap(json);

        return ResponseEntity.ok(recipeService.searchByNutrients(parameters));
    }*/

/*    private RecipeResponse recipeRequestToResponse (RecipeRequest request) {
        return new RecipeResponse(request.getRecipeId(), request.getName(), request.getImage(), request.getInstructions(), request.getReadyInMinutes(), request.getSourceUrl(), request.getServings());
    }*/
}
