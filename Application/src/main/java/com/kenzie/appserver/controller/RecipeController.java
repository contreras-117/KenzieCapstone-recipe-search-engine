package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.RecipeResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class RecipeController {
    private static final String SPOONACULAR_API_URL = "https://api.spoonacular.com";
    private static final String API_KEY = "66ada6da0bmshd09fe8ebb84e544p1f2c13jsn12e801189520";

    @GetMapping("/food/search")
    public ResponseEntity<List<RecipeResponse>> get(@RequestParam("query") String query){
        RestTemplate restTemplate = new RestTemplate();

        String url = SPOONACULAR_API_URL + "/food/search?apiKey=" + API_KEY + "&query=" + query;

        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
    }

}
