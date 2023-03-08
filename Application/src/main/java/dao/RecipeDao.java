package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.controller.model.RecipeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Component
public class RecipeDao {
    private static final String SPOONACULAR_API_URL = "https://api.spoonacular.com";
    private static final String API_KEY = "&apiKey=66ada6da0bmshd09fe8ebb84e544p1f2c13jsn12e801189520";

    @Autowired
    public RecipeDao() {
    }

    public List<RecipeResponse> getAllRecipes(){
        return null;
    }

    public RecipeResponse getRandomRecipe() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPOONACULAR_API_URL + "/random" + API_KEY))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = httpResponse.statusCode();
        if (statusCode == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(httpResponse.body(), RecipeResponse.class);
        } else {
            throw new IOException(String.format("GET request failed: %d status code received", statusCode));
        }
    }
}
