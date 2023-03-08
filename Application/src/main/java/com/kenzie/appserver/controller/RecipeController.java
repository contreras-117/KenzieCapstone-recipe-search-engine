package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.RecipeResponse;
import com.kenzie.appserver.service.RecipeService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private static final String SPOONACULAR_API_URL = "https://api.spoonacular.com";
    private static final String API_KEY = "66ada6da0bmshd09fe8ebb84e544p1f2c13jsn12e801189520";
    private RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/food/search")
    public ResponseEntity<List<RecipeResponse>> get(@RequestHeader() String query){
//        ignore for now
        RestTemplate restTemplate = new RestTemplate();

        String url = SPOONACULAR_API_URL + "/food/search?apiKey=" + API_KEY + "&query=" + query;

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

    @GetMapping("/random")
    public ResponseEntity<RecipeResponse> getRandomRecipe() throws IOException, InterruptedException {
        RecipeResponse response = recipeService.getRandomRecipe();

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(response);
    }
}
