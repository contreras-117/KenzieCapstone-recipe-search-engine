package com.kenzie.capstone.service;

import com.kenzie.capstone.service.RecipeServiceLambda.RecipeService;
import com.kenzie.capstone.service.RecipeServiceLambda.dao.RecipeDao;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.Recipe;
import com.kenzie.capstone.service.model.RecipeServiceLambdaModel.RecipeResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;

import java.util.List;

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

        List<Recipe> result = recipeService.getRandomRecipe();

        verify(recipeDao, times(1)).getRandomRecipe();
        assertNotNull(result);
    }

    /*@Test
    void searchByNutrients(){

        String query = "minCarbs=5";

        String id1 = "17281";
        String id2 = "90629";
        String id3 = "238512";
        String id4 = "255793";
        String id5 = "284420";
        String id6 = "301701";
        String id7 = "386395";
        String id8 = "568840";
        String id9 = "716723";
        String id10 = "853260";

        String json = "[\n" +
                "    {\n" +
                "        \"id\": 17281,\n" +
                "        \"title\": \"Spicy Tuna Tartare\",\n" +
                "        \"image\": \"https://spoonacular.com/recipeImages/17281-312x231.jpg\",\n" +
                "        \"imageType\": \"jpg\",\n" +
                "        \"calories\": 418,\n" +
                "        \"protein\": \"26g\",\n" +
                "        \"fat\": \"26g\",\n" +
                "        \"carbs\": \"13g\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 90629,\n" +
                "        \"title\": \"Baked Apples in White Wine\",\n" +
                "        \"image\": \"https://spoonacular.com/recipeImages/90629-312x231.jpg\",\n" +
                "        \"imageType\": \"jpg\",\n" +
                "        \"calories\": 210,\n" +
                "        \"protein\": \"1g\",\n" +
                "        \"fat\": \"3g\",\n" +
                "        \"carbs\": \"38g\"\n" +
                "    },\n" +
                "]";

        String jsonInfo1 = "{\n" +
                "    \"vegetarian\": false,\n" +
                "    \"vegan\": false,\n" +
                "    \"glutenFree\": true,\n" +
                "    \"dairyFree\": true,\n" +
                "    \"veryHealthy\": false,\n" +
                "    \"cheap\": false,\n" +
                "    \"veryPopular\": false,\n" +
                "    \"sustainable\": false,\n" +
                "    \"lowFodmap\": false,\n" +
                "    \"weightWatcherSmartPoints\": 11,\n" +
                "    \"gaps\": \"no\",\n" +
                "    \"preparationMinutes\": -1,\n" +
                "    \"cookingMinutes\": -1,\n" +
                "    \"aggregateLikes\": 5,\n" +
                "    \"healthScore\": 45,\n" +
                "    \"creditsText\": \"Cristina Ferrare\",\n" +
                "    \"sourceName\": \"Cristina Ferrare\",\n" +
                "    \"pricePerServing\": 262.01,\n" +
                "    \"extendedIngredients\": [\n" +
                "        {\n" +
                "            \"id\": 9037,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"avocado.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"avocado\",\n" +
                "            \"nameClean\": \"avocado\",\n" +
                "            \"original\": \"1 ripe avocado\",\n" +
                "            \"originalName\": \"ripe avocado\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"ripe\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11165,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"cilantro.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"cilantro\",\n" +
                "            \"nameClean\": \"cilantro\",\n" +
                "            \"original\": \"1 Tbsp cilantro, finely chopped\",\n" +
                "            \"originalName\": \"cilantro, finely chopped\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"Tbsp\",\n" +
                "            \"meta\": [\n" +
                "                \"finely chopped\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 19411,\n" +
                "            \"aisle\": \"Savory Snacks\",\n" +
                "            \"image\": \"potato-chips.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"cucumbers\",\n" +
                "            \"nameClean\": \"potato chips\",\n" +
                "            \"original\": \"2 organic cucumbers, chilled, and cut into 1/4-inch-thick slices, or 24 ruffled potato chips\",\n" +
                "            \"originalName\": \"organic cucumbers, chilled, and cut into 1/4-inch-thick slices, or 24 ruffled potato chips\",\n" +
                "            \"amount\": 2.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"organic\",\n" +
                "                \"chilled\",\n" +
                "                \"cut into 1/4-inch-thick slices, or 24 ruffled potato chips\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1002024,\n" +
                "            \"aisle\": \"Spices and Seasonings\",\n" +
                "            \"image\": \"dry-mustard.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"mustard\",\n" +
                "            \"nameClean\": \"mustard powder\",\n" +
                "            \"original\": \"1/2 tsp dry mustard\",\n" +
                "            \"originalName\": \"dry mustard\",\n" +
                "            \"amount\": 0.5,\n" +
                "            \"unit\": \"tsp\",\n" +
                "            \"meta\": [\n" +
                "                \"dry\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11216,\n" +
                "            \"aisle\": \"Produce;Ethnic Foods;Spices and Seasonings\",\n" +
                "            \"image\": \"ginger.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"ginger\",\n" +
                "            \"nameClean\": \"ginger\",\n" +
                "            \"original\": \"1 tsp ginger, peeled and grated\",\n" +
                "            \"originalName\": \"ginger, peeled and grated\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"tsp\",\n" +
                "            \"meta\": [\n" +
                "                \"grated\",\n" +
                "                \"peeled\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"tsp\",\n" +
                "                    \"unitLong\": \"teaspoon\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"tsp\",\n" +
                "                    \"unitLong\": \"teaspoon\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11979,\n" +
                "            \"aisle\": \"Canned and Jarred;Produce;Ethnic Foods\",\n" +
                "            \"image\": \"jalapeno-pepper.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"jalapeno\",\n" +
                "            \"nameClean\": \"jalapeno pepper\",\n" +
                "            \"original\": \"1 Tbsp finely chopped jalapeno\",\n" +
                "            \"originalName\": \"finely chopped jalapeno\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"Tbsp\",\n" +
                "            \"meta\": [\n" +
                "                \"finely chopped\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1082047,\n" +
                "            \"aisle\": \"Spices and Seasonings\",\n" +
                "            \"image\": \"salt.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"kosher salt\",\n" +
                "            \"nameClean\": \"kosher salt\",\n" +
                "            \"original\": \"1/4 tsp kosher salt\",\n" +
                "            \"originalName\": \"kosher salt\",\n" +
                "            \"amount\": 0.25,\n" +
                "            \"unit\": \"tsp\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 9150,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"lemon.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"lemon\",\n" +
                "            \"nameClean\": \"lemon\",\n" +
                "            \"original\": \"1 fresh lemon, cut in half\",\n" +
                "            \"originalName\": \"fresh lemon, cut in half\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"fresh\",\n" +
                "                \"cut in half\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 93830,\n" +
                "            \"aisle\": \"Ethnic Foods\",\n" +
                "            \"image\": \"mirin.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"mirin\",\n" +
                "            \"nameClean\": \"mirin\",\n" +
                "            \"original\": \"1 Tbsp mirin\",\n" +
                "            \"originalName\": \"mirin\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"Tbsp\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4042,\n" +
                "            \"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
                "            \"image\": \"peanut-oil.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"peanut oil\",\n" +
                "            \"nameClean\": \"peanut oil\",\n" +
                "            \"original\": \"1 tsp peanut oil\",\n" +
                "            \"originalName\": \"peanut oil\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"tsp\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"tsp\",\n" +
                "                    \"unitLong\": \"teaspoon\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"tsp\",\n" +
                "                    \"unitLong\": \"teaspoon\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1022053,\n" +
                "            \"aisle\": \"Ethnic Foods;Oil, Vinegar, Salad Dressing\",\n" +
                "            \"image\": \"rice-vinegar.png\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"rice wine vinegar\",\n" +
                "            \"nameClean\": \"rice vinegar\",\n" +
                "            \"original\": \"12 Tbsps rice wine vinegar\",\n" +
                "            \"originalName\": \"rice wine vinegar\",\n" +
                "            \"amount\": 12.0,\n" +
                "            \"unit\": \"Tbsps\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 12.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 12.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11291,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"spring-onions.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"scallion\",\n" +
                "            \"nameClean\": \"spring onions\",\n" +
                "            \"original\": \"1 scallion, finely chopped\",\n" +
                "            \"originalName\": \"scallion, finely chopped\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"finely chopped\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4058,\n" +
                "            \"aisle\": \"Ethnic Foods\",\n" +
                "            \"image\": \"sesame-oil.png\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"sesame oil\",\n" +
                "            \"nameClean\": \"sesame oil\",\n" +
                "            \"original\": \"1 Tbsp sesame oil\",\n" +
                "            \"originalName\": \"sesame oil\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"Tbsp\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11677,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"shallots.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"shallot\",\n" +
                "            \"nameClean\": \"shallot\",\n" +
                "            \"original\": \"1 medium shallot, diced small\",\n" +
                "            \"originalName\": \"shallot, diced small\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"medium\",\n" +
                "            \"meta\": [\n" +
                "                \"diced\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"medium\",\n" +
                "                    \"unitLong\": \"medium\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"medium\",\n" +
                "                    \"unitLong\": \"medium\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 16124,\n" +
                "            \"aisle\": \"Ethnic Foods;Condiments\",\n" +
                "            \"image\": \"soy-sauce.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"soy sauce\",\n" +
                "            \"nameClean\": \"soy sauce\",\n" +
                "            \"original\": \"1 Tbsps soy sauce\",\n" +
                "            \"originalName\": \"soy sauce\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"Tbsps\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 10015121,\n" +
                "            \"aisle\": \"Seafood\",\n" +
                "            \"image\": \"canned-tuna.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"sushi-grade tuna\",\n" +
                "            \"nameClean\": \"tuna\",\n" +
                "            \"original\": \"1/2 lb (8ozs) sushi-grade tuna; cut into small pieces, place in a bowl, cover, and refrigerate\",\n" +
                "            \"originalName\": \"1/2 lb sushi-grade tuna; cut into small pieces, place in a bowl, cover, and refrigerate\",\n" +
                "            \"amount\": 8.0,\n" +
                "            \"unit\": \"ozs\",\n" +
                "            \"meta\": [\n" +
                "                \"cut into small pieces, place in a bowl, cover, and refrigerate\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 8.0,\n" +
                "                    \"unitShort\": \"oz\",\n" +
                "                    \"unitLong\": \"ounces\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 226.796,\n" +
                "                    \"unitShort\": \"g\",\n" +
                "                    \"unitLong\": \"grams\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\": 17281,\n" +
                "    \"title\": \"Spicy Tuna Tartare\",\n" +
                "    \"readyInMinutes\": 45,\n" +
                "    \"servings\": 2,\n" +
                "    \"sourceUrl\": \"http://cristinaferrarecooks.com/2012/04/spicy-tuna-tartare/\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/17281-556x370.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"summary\": \"Spicy Tuna Tartare is a <b>gluten free, dairy free, and pescatarian</b> main course. This recipe serves 2 and costs $2.62 per serving. One portion of this dish contains around <b>26g of protein</b>, <b>26g of fat</b>, and a total of <b>418 calories</b>. Head to the store and pick up lemon, kosher salt, sesame oil, and a few other things to make it today. 5 people have tried and liked this recipe. It is brought to you by Cristina Ferrare. From preparation to the plate, this recipe takes about <b>45 minutes</b>. With a spoonacular <b>score of 93%</b>, this dish is awesome. Similar recipes include <a href=\\\"https://spoonacular.com/recipes/spicy-tuna-tartare-90069\\\">Spicy Tuna Tartare</a>, <a href=\\\"https://spoonacular.com/recipes/spicy-crunchy-tuna-tartare-80132\\\">Spicy Crunchy Tuna Tartare</a>, and <a href=\\\"https://spoonacular.com/recipes/spicy-tuna-tartare-in-sesame-miso-cones-8100\\\">Spicy Tuna Tartare In Sesame Miso Cones</a>.\",\n" +
                "    \"cuisines\": [],\n" +
                "    \"dishTypes\": [\n" +
                "        \"lunch\",\n" +
                "        \"main course\",\n" +
                "        \"main dish\",\n" +
                "        \"dinner\"\n" +
                "    ],\n" +
                "    \"diets\": [\n" +
                "        \"gluten free\",\n" +
                "        \"dairy free\",\n" +
                "        \"pescatarian\"\n" +
                "    ],\n" +
                "    \"occasions\": [],\n" +
                "    \"winePairing\": {\n" +
                "        \"pairedWines\": [\n" +
                "            \"pinot noir\",\n" +
                "            \"merlot\",\n" +
                "            \"rose wine\"\n" +
                "        ],\n" +
                "        \"pairingText\": \"Tunan on the menu? Try pairing with Pinot Noir, Merlot, and rosé Wine. Though fish is often paired with white wine, 'meatier' fish like tuna can absolutely go with red wine. A Rosé will also pair nicely, particularly if your tunan is prepared with ingredients better suited to a white wine. The Stoltz Organic Pinot Noir with a 5 out of 5 star rating seems like a good match. It costs about 26 dollars per bottle.\",\n" +
                "        \"productMatches\": [\n" +
                "            {\n" +
                "                \"id\": 428415,\n" +
                "                \"title\": \"Stoltz Organic Pinot Noir\",\n" +
                "                \"description\": \"This Pinot shows well with its deep rich color and floral aroma for an organic Columbia Gorge Pinot noir. Everything is in order for this wine to either welcome years of aging or be drunk on the spot.\",\n" +
                "                \"price\": \"$26.0\",\n" +
                "                \"imageUrl\": \"https://spoonacular.com/productImages/428415-312x231.jpg\",\n" +
                "                \"averageRating\": 1.0,\n" +
                "                \"ratingCount\": 4.0,\n" +
                "                \"score\": 0.9230769230769231,\n" +
                "                \"link\": \"https://www.amazon.com/2009-Stoltz-Organic-Pinot-Noir/dp/B00E0TG9PM?tag=spoonacular-20\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"instructions\": null,\n" +
                "    \"analyzedInstructions\": [],\n" +
                "    \"originalId\": null\n" +
                "}";

        String jsonInfo2 = "{\n" +
                "    \"vegetarian\": true,\n" +
                "    \"vegan\": false,\n" +
                "    \"glutenFree\": true,\n" +
                "    \"dairyFree\": false,\n" +
                "    \"veryHealthy\": false,\n" +
                "    \"cheap\": false,\n" +
                "    \"veryPopular\": false,\n" +
                "    \"sustainable\": false,\n" +
                "    \"lowFodmap\": false,\n" +
                "    \"weightWatcherSmartPoints\": 3,\n" +
                "    \"gaps\": \"no\",\n" +
                "    \"preparationMinutes\": 15,\n" +
                "    \"cookingMinutes\": 80,\n" +
                "    \"aggregateLikes\": 0,\n" +
                "    \"healthScore\": 0,\n" +
                "    \"creditsText\": \"Food.com\",\n" +
                "    \"sourceName\": \"Food.com\",\n" +
                "    \"pricePerServing\": 136.9,\n" +
                "    \"extendedIngredients\": [\n" +
                "        {\n" +
                "            \"id\": 9016,\n" +
                "            \"aisle\": \"Beverages\",\n" +
                "            \"image\": \"apple-juice.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"apple juice\",\n" +
                "            \"nameClean\": \"apple juice\",\n" +
                "            \"original\": \"1/4 cup apple juice\",\n" +
                "            \"originalName\": \"apple juice\",\n" +
                "            \"amount\": 0.25,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 59.147,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 19334,\n" +
                "            \"aisle\": \"Baking\",\n" +
                "            \"image\": \"light-brown-sugar.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"firmly-packed brown sugar\",\n" +
                "            \"nameClean\": \"golden brown sugar\",\n" +
                "            \"original\": \"2 tablespoons firmly-packed light brown sugar\",\n" +
                "            \"originalName\": \"firmly-packed light brown sugar\",\n" +
                "            \"amount\": 2.0,\n" +
                "            \"unit\": \"tablespoons\",\n" +
                "            \"meta\": [\n" +
                "                \"light\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1001,\n" +
                "            \"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
                "            \"image\": \"butter-sliced.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"butter\",\n" +
                "            \"nameClean\": \"butter\",\n" +
                "            \"original\": \"1 tablespoon butter\",\n" +
                "            \"originalName\": \"butter\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"tablespoon\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"Tbsp\",\n" +
                "                    \"unitLong\": \"Tbsp\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2010,\n" +
                "            \"aisle\": \"Spices and Seasonings\",\n" +
                "            \"image\": \"cinnamon.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"cinnamon\",\n" +
                "            \"nameClean\": \"cinnamon\",\n" +
                "            \"original\": \"1/4 teaspoon cinnamon\",\n" +
                "            \"originalName\": \"cinnamon\",\n" +
                "            \"amount\": 0.25,\n" +
                "            \"unit\": \"teaspoon\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"tsps\",\n" +
                "                    \"unitLong\": \"teaspoons\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 14106,\n" +
                "            \"aisle\": \"Alcoholic Beverages\",\n" +
                "            \"image\": \"white-wine.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"wine\",\n" +
                "            \"nameClean\": \"dry white wine\",\n" +
                "            \"original\": \"1/2 cup dry white wine\",\n" +
                "            \"originalName\": \"dry white wine\",\n" +
                "            \"amount\": 0.5,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [\n" +
                "                \"dry white\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 118.294,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 9299,\n" +
                "            \"aisle\": \"Dried Fruits;Produce;Baking\",\n" +
                "            \"image\": \"raisins.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"sultana raisin\",\n" +
                "            \"nameClean\": \"raisins\",\n" +
                "            \"original\": \"1/3 cup sultana raisin\",\n" +
                "            \"originalName\": \"sultana raisin\",\n" +
                "            \"amount\": 0.33333334,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.333,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 78.863,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1059003,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"red-delicious-apples.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"delicious apples\",\n" +
                "            \"nameClean\": \"red delicious apple\",\n" +
                "            \"original\": \"4 red delicious apples\",\n" +
                "            \"originalName\": \"red delicious apples\",\n" +
                "            \"amount\": 4.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"red\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 4.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 4.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\": 90629,\n" +
                "    \"title\": \"Baked Apples in White Wine\",\n" +
                "    \"readyInMinutes\": 95,\n" +
                "    \"servings\": 4,\n" +
                "    \"sourceUrl\": \"http://www.food.com/recipe/baked-apples-in-white-wine-60884\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/90629-556x370.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"summary\": \"Baked Apples in White Wine might be a good recipe to expand your dessert repertoire. For <b>$1.37 per serving</b>, this recipe <b>covers 4%</b> of your daily requirements of vitamins and minerals. Watching your figure? This gluten free and lacto ovo vegetarian recipe has <b>210 calories</b>, <b>1g of protein</b>, and <b>3g of fat</b> per serving. This recipe serves 4. If you have sultana raisin, scrumptious apples, cinnamon, and a few other ingredients on hand, you can make it. It is brought to you by Food.com. From preparation to the plate, this recipe takes about <b>1 hour and 35 minutes</b>. This recipe is liked by 1 foodies and cooks. All things considered, we decided this recipe <b>deserves a spoonacular score of 14%</b>. This score is rather bad. Users who liked this recipe also liked <a href=\\\"https://spoonacular.com/recipes/a-simple-yet-elegant-dessert-baked-apples-in-red-wine-515386\\\">A Simple Yet Elegant Dessert – Baked Apples In Red Wine</a>, <a href=\\\"https://spoonacular.com/recipes/white-wine-garlic-baked-shrimp-941543\\\">White Wine Garlic Baked Shrimp</a>, and <a href=\\\"https://spoonacular.com/recipes/oven-baked-fish-in-white-wine-91152\\\">Oven Baked Fish in White Wine</a>.\",\n" +
                "    \"cuisines\": [],\n" +
                "    \"dishTypes\": [\n" +
                "        \"dessert\"\n" +
                "    ],\n" +
                "    \"diets\": [\n" +
                "        \"gluten free\",\n" +
                "        \"lacto ovo vegetarian\"\n" +
                "    ],\n" +
                "    \"occasions\": [],\n" +
                "    \"winePairing\": {\n" +
                "        \"pairedWines\": [\n" +
                "            \"cream sherry\",\n" +
                "            \"port\",\n" +
                "            \"moscato dasti\"\n" +
                "        ],\n" +
                "        \"pairingText\": \"Baked Apple works really well with Cream Sherry, Port, and Moscato d'Asti. A common wine pairing rule is to make sure your wine is sweeter than your food. Delicate desserts go well with Moscato d'Asti, nutty desserts with cream sherry, and caramel or chocolate desserts pair well with port. The NV Johnson Estate Cream Sherry with a 5 out of 5 star rating seems like a good match. It costs about 19 dollars per bottle.\",\n" +
                "        \"productMatches\": [\n" +
                "            {\n" +
                "                \"id\": 430626,\n" +
                "                \"title\": \"NV Johnson Estate Cream Sherry\",\n" +
                "                \"description\": \"Very aromatic with notes of hazelnut, vanilla, and a touch of oak followed by sweet raisins and a touch of yeast. Clean lasting finish. Good now but will reward those allow it to age\\\"\\\". A favorite pre-prandial beverage. Consider it with nuts before dinner as an aperitif, or after dinner with dessert, especially chocolates and fruit-based desserts. Also wonderful on cold afternoons, served with biscotti to dip in \\\"\\\"Italian-style\\\"\\\". \\\"\",\n" +
                "                \"price\": \"$19.49\",\n" +
                "                \"imageUrl\": \"https://spoonacular.com/productImages/430626-312x231.jpg\",\n" +
                "                \"averageRating\": 1.0,\n" +
                "                \"ratingCount\": 2.0,\n" +
                "                \"score\": 0.8571428571428572,\n" +
                "                \"link\": \"https://www.amazon.com/Johnson-Estate-Cream-Sherry-750/dp/B00D3GQSRW?tag=spoonacular-20\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"instructions\": null,\n" +
                "    \"analyzedInstructions\": [],\n" +
                "    \"originalId\": null\n" +
                "}";

        when(recipeDao.searchByNutrients(query)).thenReturn(json);
        when(recipeDao.getRecipeInformation(id1)).thenReturn(jsonInfo1);
        when(recipeDao.getRecipeInformation(id2)).thenReturn(jsonInfo2);

        List<RecipeResponse> result = recipeService.searchByNutrients(query);

        System.out.println(result.get(0).getRecipeId() + "\n" + result.get(1).getRecipeId());

        verify(recipeDao, times(1)).searchByNutrients(query);
        *//*verify(recipeDao, times(10)).getRecipeInformation(recipeId);*//*
        assertNotNull(result);
    }*/

    @Test
    void getRecipeInformation() {
        String recipeId = "20183";

        String jsonResponse = "{\n" +
                "    \"vegetarian\": true,\n" +
                "    \"vegan\": false,\n" +
                "    \"glutenFree\": false,\n" +
                "    \"dairyFree\": false,\n" +
                "    \"veryHealthy\": true,\n" +
                "    \"cheap\": false,\n" +
                "    \"veryPopular\": false,\n" +
                "    \"sustainable\": false,\n" +
                "    \"lowFodmap\": false,\n" +
                "    \"weightWatcherSmartPoints\": 11,\n" +
                "    \"gaps\": \"no\",\n" +
                "    \"preparationMinutes\": -1,\n" +
                "    \"cookingMinutes\": -1,\n" +
                "    \"aggregateLikes\": 0,\n" +
                "    \"healthScore\": 64,\n" +
                "    \"creditsText\": \"Food and Wine\",\n" +
                "    \"sourceName\": \"Food and Wine\",\n" +
                "    \"pricePerServing\": 315.73,\n" +
                "    \"extendedIngredients\": [\n" +
                "        {\n" +
                "            \"id\": 11457,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"spinach.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"baby spinach\",\n" +
                "            \"nameClean\": \"baby spinach\",\n" +
                "            \"original\": \"1 pound baby spinach\",\n" +
                "            \"originalName\": \"baby spinach\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"pound\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"lb\",\n" +
                "                    \"unitLong\": \"pound\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 453.592,\n" +
                "                    \"unitShort\": \"g\",\n" +
                "                    \"unitLong\": \"grams\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 18079,\n" +
                "            \"aisle\": \"Pasta and Rice\",\n" +
                "            \"image\": \"breadcrumbs.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"coarse bread crumbs\",\n" +
                "            \"nameClean\": \"breadcrumbs\",\n" +
                "            \"original\": \"1 1/2 cups panko or coarse dry bread crumbs\",\n" +
                "            \"originalName\": \"panko or coarse dry bread crumbs\",\n" +
                "            \"amount\": 1.5,\n" +
                "            \"unit\": \"cups\",\n" +
                "            \"meta\": [\n" +
                "                \"dry\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.5,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 354.882,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20027,\n" +
                "            \"aisle\": \"Baking\",\n" +
                "            \"image\": \"white-powder.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"cornstarch\",\n" +
                "            \"nameClean\": \"corn starch\",\n" +
                "            \"original\": \"1/2 cup cornstarch\",\n" +
                "            \"originalName\": \"cornstarch\",\n" +
                "            \"amount\": 0.5,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 118.294,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1032046,\n" +
                "            \"aisle\": \"Condiments\",\n" +
                "            \"image\": \"dijon-mustard.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"dijon mustard\",\n" +
                "            \"nameClean\": \"dijon mustard\",\n" +
                "            \"original\": \"Dijon mustard, for brushing\",\n" +
                "            \"originalName\": \"Dijon mustard, for brushing\",\n" +
                "            \"amount\": 6.0,\n" +
                "            \"unit\": \"servings\",\n" +
                "            \"meta\": [\n" +
                "                \"for brushing\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 6.0,\n" +
                "                    \"unitShort\": \"servings\",\n" +
                "                    \"unitLong\": \"servings\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 6.0,\n" +
                "                    \"unitShort\": \"servings\",\n" +
                "                    \"unitLong\": \"servings\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 20081,\n" +
                "            \"aisle\": \"Baking\",\n" +
                "            \"image\": \"flour.png\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"flour\",\n" +
                "            \"nameClean\": \"wheat flour\",\n" +
                "            \"original\": \"1/2 cup all-purpose flour\",\n" +
                "            \"originalName\": \"all-purpose flour\",\n" +
                "            \"amount\": 0.5,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [\n" +
                "                \"all-purpose\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 118.294,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 10211215,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"garlic.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"garlic clove\",\n" +
                "            \"nameClean\": \"whole garlic cloves\",\n" +
                "            \"original\": \"1 garlic clove, minced\",\n" +
                "            \"originalName\": \"garlic clove, minced\",\n" +
                "            \"amount\": 1.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"minced\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 1.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 93820,\n" +
                "            \"aisle\": \"Cheese\",\n" +
                "            \"image\": \"white-cream-fluffy.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"mascarpone\",\n" +
                "            \"nameClean\": \"mascarpone\",\n" +
                "            \"original\": \"1/4 cup mascarpone\",\n" +
                "            \"originalName\": \"mascarpone\",\n" +
                "            \"amount\": 0.25,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.25,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 59.147,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4053,\n" +
                "            \"aisle\": \"Oil, Vinegar, Salad Dressing\",\n" +
                "            \"image\": \"olive-oil.jpg\",\n" +
                "            \"consistency\": \"LIQUID\",\n" +
                "            \"name\": \"olive oil\",\n" +
                "            \"nameClean\": \"olive oil\",\n" +
                "            \"original\": \"1/2 cup pure olive oil\",\n" +
                "            \"originalName\": \"pure olive oil\",\n" +
                "            \"amount\": 0.5,\n" +
                "            \"unit\": \"cup\",\n" +
                "            \"meta\": [\n" +
                "                \"pure\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 0.5,\n" +
                "                    \"unitShort\": \"cups\",\n" +
                "                    \"unitLong\": \"cups\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 118.294,\n" +
                "                    \"unitShort\": \"ml\",\n" +
                "                    \"unitLong\": \"milliliters\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1102047,\n" +
                "            \"aisle\": \"Spices and Seasonings\",\n" +
                "            \"image\": \"salt-and-pepper.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"salt and pepper\",\n" +
                "            \"nameClean\": \"salt and pepper\",\n" +
                "            \"original\": \"Salt and freshly ground pepper\",\n" +
                "            \"originalName\": \"Salt and freshly ground pepper\",\n" +
                "            \"amount\": 6.0,\n" +
                "            \"unit\": \"servings\",\n" +
                "            \"meta\": [\n" +
                "                \"freshly ground\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 6.0,\n" +
                "                    \"unitShort\": \"servings\",\n" +
                "                    \"unitLong\": \"servings\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 6.0,\n" +
                "                    \"unitShort\": \"servings\",\n" +
                "                    \"unitLong\": \"servings\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11677,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"shallots.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"shallots\",\n" +
                "            \"nameClean\": \"shallot\",\n" +
                "            \"original\": \"2 shallots, minced\",\n" +
                "            \"originalName\": \"shallots, minced\",\n" +
                "            \"amount\": 2.0,\n" +
                "            \"unit\": \"\",\n" +
                "            \"meta\": [\n" +
                "                \"minced\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 2.0,\n" +
                "                    \"unitShort\": \"\",\n" +
                "                    \"unitLong\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 11147,\n" +
                "            \"aisle\": \"Produce\",\n" +
                "            \"image\": \"swiss-chard.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"swiss chard\",\n" +
                "            \"nameClean\": \"swiss chard\",\n" +
                "            \"original\": \"5 pounds Swiss chard (about 3 bunches), thick stems discarded\",\n" +
                "            \"originalName\": \"Swiss chard (about 3 bunches), thick stems discarded\",\n" +
                "            \"amount\": 5.0,\n" +
                "            \"unit\": \"pounds\",\n" +
                "            \"meta\": [\n" +
                "                \"thick\",\n" +
                "                \"( 3 bunches)\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 5.0,\n" +
                "                    \"unitShort\": \"lb\",\n" +
                "                    \"unitLong\": \"pounds\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 2.268,\n" +
                "                    \"unitShort\": \"kgs\",\n" +
                "                    \"unitLong\": \"kgs\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 1145,\n" +
                "            \"aisle\": \"Milk, Eggs, Other Dairy\",\n" +
                "            \"image\": \"butter-sliced.jpg\",\n" +
                "            \"consistency\": \"SOLID\",\n" +
                "            \"name\": \"butter\",\n" +
                "            \"nameClean\": \"unsalted butter\",\n" +
                "            \"original\": \"3 tablespoons unsalted butter\",\n" +
                "            \"originalName\": \"unsalted butter\",\n" +
                "            \"amount\": 3.0,\n" +
                "            \"unit\": \"tablespoons\",\n" +
                "            \"meta\": [\n" +
                "                \"unsalted\"\n" +
                "            ],\n" +
                "            \"measures\": {\n" +
                "                \"us\": {\n" +
                "                    \"amount\": 3.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                },\n" +
                "                \"metric\": {\n" +
                "                    \"amount\": 3.0,\n" +
                "                    \"unitShort\": \"Tbsps\",\n" +
                "                    \"unitLong\": \"Tbsps\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"id\": 20183,\n" +
                "    \"title\": \"Crispy Swiss Chard Cakes with Mascarpone-Creamed Spinach\",\n" +
                "    \"readyInMinutes\": 90,\n" +
                "    \"servings\": 6,\n" +
                "    \"sourceUrl\": \"http://www.foodandwine.com/recipes/crispy-swiss-chard-cakes-with-mascarpone-creamed-spinach\",\n" +
                "    \"image\": \"https://spoonacular.com/recipeImages/20183-556x370.jpg\",\n" +
                "    \"imageType\": \"jpg\",\n" +
                "    \"summary\": \"Crispy Swiss Chard Cakes with Mascarpone-Creamed Spinach requires about <b>1 hour and 30 minutes</b> from start to finish. This recipe serves 6. One serving contains <b>408 calories</b>, <b>15g of protein</b>, and <b>16g of fat</b>. For <b>$3.16 per serving</b>, this recipe <b>covers 44%</b> of your daily requirements of vitamins and minerals. 1 person were glad they tried this recipe. It is a good option if you're following a <b>lacto ovo vegetarian</b> diet. If you have flour, coarse bread crumbs, butter, and a few other ingredients on hand, you can make it. It is brought to you by Food and Wine. With a spoonacular <b>score of 79%</b>, this dish is solid. If you like this recipe, you might also like recipes such as <a href=\\\"https://spoonacular.com/recipes/creamed-swiss-chard-101596\\\">Creamed Swiss Chard</a>, <a href=\\\"https://spoonacular.com/recipes/creamed-swiss-chard-739932\\\">Creamed Swiss Chard</a>, and <a href=\\\"https://spoonacular.com/recipes/creamed-swiss-chard-101611\\\">Creamed Swiss Chard</a>.\",\n" +
                "    \"cuisines\": [],\n" +
                "    \"dishTypes\": [],\n" +
                "    \"diets\": [\n" +
                "        \"lacto ovo vegetarian\"\n" +
                "    ],\n" +
                "    \"occasions\": [],\n" +
                "    \"winePairing\": {\n" +
                "        \"pairedWines\": [\n" +
                "            \"cream sherry\",\n" +
                "            \"port\",\n" +
                "            \"moscato dasti\"\n" +
                "        ],\n" +
                "        \"pairingText\": \"Cake works really well with Cream Sherry, Port, and Moscato d'Asti. A common wine pairing rule is to make sure your wine is sweeter than your food. Delicate desserts go well with Moscato d'Asti, nutty desserts with cream sherry, and caramel or chocolate desserts pair well with port. The NV Solera Cream Sherry with a 4.5 out of 5 star rating seems like a good match. It costs about 17 dollars per bottle.\",\n" +
                "        \"productMatches\": [\n" +
                "            {\n" +
                "                \"id\": 428475,\n" +
                "                \"title\": \"NV Solera Cream Sherry\",\n" +
                "                \"description\": \"The Solera Cream Sherry has a brilliant amber and deep copper hue. With butterscotch and pecan aromas, the sweet salted nut and brown spice aromas carry a complex caramel accent. A sweet entry leads to a rounded, lush, moderately full-bodied palate with a lengthy, flavorful finish.\",\n" +
                "                \"price\": \"$16.99\",\n" +
                "                \"imageUrl\": \"https://spoonacular.com/productImages/428475-312x231.jpg\",\n" +
                "                \"averageRating\": 0.9,\n" +
                "                \"ratingCount\": 4.0,\n" +
                "                \"score\": 0.823076923076923,\n" +
                "                \"link\": \"https://www.amazon.com/NV-Solera-Cream-Sherry-750/dp/B00HSME8OW?tag=spoonacular-20\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"instructions\": \"In a large pot, melt 2 tablespoons of the butter. Add half of the minced shallots and all of the garlic and cook over moderate heat until softened. Add the Swiss chard and cook, tossing, until wilted. Transfer the chard to a colander and let cool. Press out as much liquid as possible and coarsely chop. Season with salt and pepper and press the chard into 6 patties; don't worry if they don't hold together.                                              In a small bowl, whisk the flour with the cornstarch and 1 teaspoon of salt. Sprinkle the mixture onto a work surface and set the chard cakes on top. Turn the cakes, pressing and squeezing them into compact patties and working in a little of the flour mixture as you go.                                              Spread the panko on a plate. Brush the tops of the cakes with mustard and invert them one at a time into the panko. Brush the bottoms with mustard and sprinkle panko on top; press to adhere. Transfer the cakes to a wax paperlined plate and refrigerate until firm, about 20 minutes.                                              In a large skillet, melt the remaining 1 tablespoon of butter. Add the remaining minced shallot and cook over moderate heat until softened. Add the spinach and cook, tossing, until wilted, about 5 minutes. Transfer the spinach to a colander and press out as much liquid as possible. Return the spinach to the skillet. Stir in the mascarpone and season with salt and pepper. Keep warm.                                              Heat the olive oil in a large nonstick skillet. Add the chard cakes and fry over moderately high heat, turning once, until golden and crisp, about 6 minutes. Spoon the spinach onto plates and top with the chard cakes. Serve right away.\",\n" +
                "    \"analyzedInstructions\": [\n" +
                "        {\n" +
                "            \"name\": \"\",\n" +
                "            \"steps\": [\n" +
                "                {\n" +
                "                    \"number\": 1,\n" +
                "                    \"step\": \"In a large pot, melt 2 tablespoons of the butter.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 1001,\n" +
                "                            \"name\": \"butter\",\n" +
                "                            \"localizedName\": \"butter\",\n" +
                "                            \"image\": \"butter-sliced.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404752,\n" +
                "                            \"name\": \"pot\",\n" +
                "                            \"localizedName\": \"pot\",\n" +
                "                            \"image\": \"stock-pot.jpg\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 2,\n" +
                "                    \"step\": \"Add half of the minced shallots and all of the garlic and cook over moderate heat until softened.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 11677,\n" +
                "                            \"name\": \"shallot\",\n" +
                "                            \"localizedName\": \"shallot\",\n" +
                "                            \"image\": \"shallots.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 11215,\n" +
                "                            \"name\": \"garlic\",\n" +
                "                            \"localizedName\": \"garlic\",\n" +
                "                            \"image\": \"garlic.png\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 3,\n" +
                "                    \"step\": \"Add the Swiss chard and cook, tossing, until wilted.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 11147,\n" +
                "                            \"name\": \"swiss chard\",\n" +
                "                            \"localizedName\": \"swiss chard\",\n" +
                "                            \"image\": \"swiss-chard.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 4,\n" +
                "                    \"step\": \"Transfer the chard to a colander and let cool. Press out as much liquid as possible and coarsely chop. Season with salt and pepper and press the chard into 6 patties; don't worry if they don't hold together.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 1102047,\n" +
                "                            \"name\": \"salt and pepper\",\n" +
                "                            \"localizedName\": \"salt and pepper\",\n" +
                "                            \"image\": \"salt-and-pepper.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 11147,\n" +
                "                            \"name\": \"swiss chard\",\n" +
                "                            \"localizedName\": \"swiss chard\",\n" +
                "                            \"image\": \"swiss-chard.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404639,\n" +
                "                            \"name\": \"colander\",\n" +
                "                            \"localizedName\": \"colander\",\n" +
                "                            \"image\": \"colander.jpg\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 5,\n" +
                "                    \"step\": \"In a small bowl, whisk the flour with the cornstarch and 1 teaspoon of salt.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 20027,\n" +
                "                            \"name\": \"corn starch\",\n" +
                "                            \"localizedName\": \"corn starch\",\n" +
                "                            \"image\": \"white-powder.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 20081,\n" +
                "                            \"name\": \"all purpose flour\",\n" +
                "                            \"localizedName\": \"all purpose flour\",\n" +
                "                            \"image\": \"flour.png\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 2047,\n" +
                "                            \"name\": \"salt\",\n" +
                "                            \"localizedName\": \"salt\",\n" +
                "                            \"image\": \"salt.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404661,\n" +
                "                            \"name\": \"whisk\",\n" +
                "                            \"localizedName\": \"whisk\",\n" +
                "                            \"image\": \"whisk.png\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 404783,\n" +
                "                            \"name\": \"bowl\",\n" +
                "                            \"localizedName\": \"bowl\",\n" +
                "                            \"image\": \"bowl.jpg\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 6,\n" +
                "                    \"step\": \"Sprinkle the mixture onto a work surface and set the chard cakes on top. Turn the cakes, pressing and squeezing them into compact patties and working in a little of the flour mixture as you go.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 11147,\n" +
                "                            \"name\": \"swiss chard\",\n" +
                "                            \"localizedName\": \"swiss chard\",\n" +
                "                            \"image\": \"swiss-chard.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 20081,\n" +
                "                            \"name\": \"all purpose flour\",\n" +
                "                            \"localizedName\": \"all purpose flour\",\n" +
                "                            \"image\": \"flour.png\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 7,\n" +
                "                    \"step\": \"Spread the panko on a plate.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 0,\n" +
                "                            \"name\": \"spread\",\n" +
                "                            \"localizedName\": \"spread\",\n" +
                "                            \"image\": \"\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 10018079,\n" +
                "                            \"name\": \"panko\",\n" +
                "                            \"localizedName\": \"panko\",\n" +
                "                            \"image\": \"panko.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 8,\n" +
                "                    \"step\": \"Brush the tops of the cakes with mustard and invert them one at a time into the panko.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 2046,\n" +
                "                            \"name\": \"mustard\",\n" +
                "                            \"localizedName\": \"mustard\",\n" +
                "                            \"image\": \"regular-mustard.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 10018079,\n" +
                "                            \"name\": \"panko\",\n" +
                "                            \"localizedName\": \"panko\",\n" +
                "                            \"image\": \"panko.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 9,\n" +
                "                    \"step\": \"Brush the bottoms with mustard and sprinkle panko on top; press to adhere.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 2046,\n" +
                "                            \"name\": \"mustard\",\n" +
                "                            \"localizedName\": \"mustard\",\n" +
                "                            \"image\": \"regular-mustard.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 10018079,\n" +
                "                            \"name\": \"panko\",\n" +
                "                            \"localizedName\": \"panko\",\n" +
                "                            \"image\": \"panko.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 10,\n" +
                "                    \"step\": \"Transfer the cakes to a wax paperlined plate and refrigerate until firm, about 20 minutes.\",\n" +
                "                    \"ingredients\": [],\n" +
                "                    \"equipment\": [],\n" +
                "                    \"length\": {\n" +
                "                        \"number\": 20,\n" +
                "                        \"unit\": \"minutes\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 11,\n" +
                "                    \"step\": \"In a large skillet, melt the remaining 1 tablespoon of butter.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 1001,\n" +
                "                            \"name\": \"butter\",\n" +
                "                            \"localizedName\": \"butter\",\n" +
                "                            \"image\": \"butter-sliced.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404645,\n" +
                "                            \"name\": \"frying pan\",\n" +
                "                            \"localizedName\": \"frying pan\",\n" +
                "                            \"image\": \"pan.png\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 12,\n" +
                "                    \"step\": \"Add the remaining minced shallot and cook over moderate heat until softened.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 11677,\n" +
                "                            \"name\": \"shallot\",\n" +
                "                            \"localizedName\": \"shallot\",\n" +
                "                            \"image\": \"shallots.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": []\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 13,\n" +
                "                    \"step\": \"Add the spinach and cook, tossing, until wilted, about 5 minutes.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 10011457,\n" +
                "                            \"name\": \"spinach\",\n" +
                "                            \"localizedName\": \"spinach\",\n" +
                "                            \"image\": \"spinach.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [],\n" +
                "                    \"length\": {\n" +
                "                        \"number\": 5,\n" +
                "                        \"unit\": \"minutes\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 14,\n" +
                "                    \"step\": \"Transfer the spinach to a colander and press out as much liquid as possible. Return the spinach to the skillet. Stir in the mascarpone and season with salt and pepper. Keep warm.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 1102047,\n" +
                "                            \"name\": \"salt and pepper\",\n" +
                "                            \"localizedName\": \"salt and pepper\",\n" +
                "                            \"image\": \"salt-and-pepper.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 93820,\n" +
                "                            \"name\": \"mascarpone\",\n" +
                "                            \"localizedName\": \"mascarpone\",\n" +
                "                            \"image\": \"white-cream-fluffy.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 10011457,\n" +
                "                            \"name\": \"spinach\",\n" +
                "                            \"localizedName\": \"spinach\",\n" +
                "                            \"image\": \"spinach.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404639,\n" +
                "                            \"name\": \"colander\",\n" +
                "                            \"localizedName\": \"colander\",\n" +
                "                            \"image\": \"colander.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 404645,\n" +
                "                            \"name\": \"frying pan\",\n" +
                "                            \"localizedName\": \"frying pan\",\n" +
                "                            \"image\": \"pan.png\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 15,\n" +
                "                    \"step\": \"Heat the olive oil in a large nonstick skillet.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 4053,\n" +
                "                            \"name\": \"olive oil\",\n" +
                "                            \"localizedName\": \"olive oil\",\n" +
                "                            \"image\": \"olive-oil.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [\n" +
                "                        {\n" +
                "                            \"id\": 404645,\n" +
                "                            \"name\": \"frying pan\",\n" +
                "                            \"localizedName\": \"frying pan\",\n" +
                "                            \"image\": \"pan.png\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 16,\n" +
                "                    \"step\": \"Add the chard cakes and fry over moderately high heat, turning once, until golden and crisp, about 6 minutes. Spoon the spinach onto plates and top with the chard cakes.\",\n" +
                "                    \"ingredients\": [\n" +
                "                        {\n" +
                "                            \"id\": 10011457,\n" +
                "                            \"name\": \"spinach\",\n" +
                "                            \"localizedName\": \"spinach\",\n" +
                "                            \"image\": \"spinach.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 11147,\n" +
                "                            \"name\": \"swiss chard\",\n" +
                "                            \"localizedName\": \"swiss chard\",\n" +
                "                            \"image\": \"swiss-chard.jpg\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"equipment\": [],\n" +
                "                    \"length\": {\n" +
                "                        \"number\": 6,\n" +
                "                        \"unit\": \"minutes\"\n" +
                "                    }\n" +
                "                },\n" +
                "                {\n" +
                "                    \"number\": 17,\n" +
                "                    \"step\": \"Serve right away.\",\n" +
                "                    \"ingredients\": [],\n" +
                "                    \"equipment\": []\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"originalId\": null\n" +
                "}";

        when(recipeDao.getRecipeInformation(recipeId)).thenReturn(jsonResponse);

        RecipeResponse result = recipeService.getRecipeInformation(recipeId);

        System.out.println(result.getRecipeId());
        System.out.println(result.getImage());
        System.out.println(result.getInstructions());
        System.out.println(result.getName());
        System.out.println(result.getServings());
        System.out.println(result.getReadyInMinutes());
        System.out.println(result.getSourceUrl());

        verify(recipeDao, times(1)).getRecipeInformation(recipeId);
        assertEquals(recipeId, result.getRecipeId());
        assertNotNull(result);

    }
}
