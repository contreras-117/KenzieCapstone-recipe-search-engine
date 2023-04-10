import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import RecipeClient from "../api/recipeClient";
import ChefMateClient from "../api/chefMateClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class HomePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onSearchByNutrients', 'onSearchByIngredients', 'onGetAllRecipes', 'onGetRandomRecipe',
            'onUpdateUserPreference', 'onUpdateRecipesTried', 'onDeleteUser'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('search-by-nutrients-form').addEventListener('submit', this.onSearchByNutrients);
        document.getElementById('get-by-id-form').addEventListener('submit', this.onSearchByIngredients);
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGetAllRecipes);
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGetRandomRecipe);
        document.getElementById('update-preference').addEventListener('submit', this.onUpdateUserPreference);
        document.getElementById('update-recipes-tried').addEventListener('submit', this.onUpdateRecipesTried);
        document.getElementById('delete-user').addEventListener('click', this.onDeleteUser);
        this.client = new RecipeClient();
        this.client = new ChefMateClient();

        /*this.dataStore.addChangeListener(this.renderExample)*/
    }

    // Render Methods --------------------------------------------------------------------------------------------------

/*    async renderExample() {
        let resultArea = document.getElementById("result-info");

        const example = this.dataStore.get("example");

        if (example) {
            resultArea.innerHTML = `
                <div>ID: ${example.id}</div>
                <div>Name: ${example.name}</div>
            `
        } else {
            resultArea.innerHTML = "No Item";
        }
    }*/

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onSearchByNutrients(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let loadingSpinner = document.getElementById("spinner");

        loadingSpinner.style.display = "block";

        let minCarbs = document.getElementById("min-carbs-input").value;
        let maxCarbs = document.getElementById("max-carbs-input").value;

        let minProtein = document.getElementById("min-protein-input").value;
        let maxProtein = document.getElementById("max-protein-input").value;

        let minCalories = document.getElementById("min-calories-input").value;
        let maxCalories = document.getElementById("max-calories-input").value;

        let minSodium = document.getElementById("min-sodium-input").value;
        let maxSodium = document.getElementById("max-sodium-input").value;

        let minSugar = document.getElementById("min-sugar-input").value;
        let maxSugar = document.getElementById("max-sugar-input").value;

        let minFiber = document.getElementById("min-fiber-input").value;
        let maxFiber = document.getElementById("max-fiber-input").value;

        let minIron = document.getElementById("min-iron-input").value;
        let maxIron = document.getElementById("max-iron-input").value;

        let minCholesterol = document.getElementById("min-cholesterol-input").value;
        let maxCholesterol = document.getElementById("max-cholesterol-input").value;

        let query = "";

        if (minCarbs !== "undefined" && minCarbs !== "" && minCarbs !== null && minCarbs !== 0) {
            query += "minCarbs=" + minCarbs + "&";
        }
        if (maxCarbs !== "undefined" && maxCarbs !== "" && maxCarbs !== null && maxCarbs !== 0) {
            query += "maxCarbs=" + maxCarbs + "&";;
        }
        if (minProtein !== "undefined" && minProtein !== "" && minProtein !== null && minProtein !== 0) {
            query += "minProtein=" + minProtein + "&";
        }
        if (maxProtein !== "undefined" && maxProtein !== "" && maxProtein !== null && maxProtein !== 0) {
            query += "maxProtein=" + maxProtein + "&";
        }
        if (minCalories !== "undefined" && minCalories !== "" && minCalories !== null && minCalories !== 0) {
            query += "minCalories=" + minCalories + "&";
        }
        if (maxCalories !== "undefined" && maxCalories !== "" && maxCalories !== null && maxCalories !== 0) {
            query += "maxCalories=" + maxCalories + "&";
        }
        if (minSodium !== "undefined" && minSodium !== "" && minSodium !== null && minSodium !== 0) {
            query += "minSodium=" + minSodium + "&";
        }
        if (maxSodium !== "undefined" && maxSodium !== "" && maxSodium !== null && maxSodium !== 0) {
            query += "maxSodium=" + maxSodium + "&";
        }
        if (minSugar !== "undefined" && minSugar !== "" && minSugar !== null && minSugar !== 0) {
            query += "minSugar=" + minSugar + "&";
        }
        if (maxSugar !== "undefined" && maxSugar !== "" && maxSugar !== null && maxSugar !== 0) {
            query += "maxSugar=" + maxSugar + "&";
        }
        if (minFiber !== "undefined" && minFiber !== "" && minFiber !== null && minFiber !== 0) {
            query += "minFiber=" + minFiber + "&";
        }
        if (maxFiber !== "undefined" && maxFiber !== "" && maxFiber !== null && maxFiber !== 0) {
            query += "maxFiber=" + maxFiber + "&";
        }
        if (minIron !== "undefined" && minIron !== "" && minIron !== null && minIron !== 0) {
            query += "maxFiber=" + minIron + "&";
        }
        if (maxIron !== "undefined" && maxIron !== "" && maxIron !== null && maxIron !== 0) {
            query += "maxIron=" + maxIron + "&";
        }
        if (minCholesterol !== "undefined" && minCholesterol !== "" && minCholesterol !== null && minCholesterol !== 0) {
            query += "minCholesterol=" + minCholesterol + "&";
        }
        if (maxCholesterol !== "undefined" && maxCholesterol !== "" && maxCholesterol !== null && maxCholesterol !== 0) {
            query += "maxCholesterol=" + maxCholesterol + "&";
        }

        if (query.charAt(query.length - 1) == "&") {
            query = query.slice(0, query.length - 1);
        }

        query += "&number=10";

        // needs userId data to pass in
        let result = await this.client.searchByNutrients(userId, query, this.errorHandler);
        this.dataStore.set("searchByNutrients", result);

        let renderingSection = document.getElementById("rendering-recipes-section");

        let recipesReturned = this.dataStore.get("searchByNutrients");

        let html = "";

        for (let recipe of recipesReturned) {
            let instructionsString = recipe.instructions;


            if (instructionsString === "undefined" || instructionsString == null) {
                instructionsString = "Oops! It seems like we have encountered a recipe without instructions. Please refer to the link instead!"
            } else {
                instructionsString = instructionsString.split("'").join('');
            }

            html += "<div class='recipes'>";
            html += "<div class='content'>";
            html += `<img class="recipe-image" src=${recipe.image}>`;
            html += "<div class='recipe-title'>";
            html += `<p><strong>${recipe.title}</strong></p>`;
            html += "</div>";
            html += "<div class='recipe-content'>";
            html += "<ul>";
            html += `<li>Recipe ID: ${recipe.id}</li>`;
            html += `<li>Ready In: ${recipe.readyInMinutes} minutes</li>`;
            html += `<li>Servings: ${recipe.servings}</li>`;
            html += "</ul>";
            html += "</div>";
            html += `<a href=${recipe.sourceUrl} rel="noopener noreferrer" target="_blank"><button class='instructions-button'>Instructions</button></a>`;
            html += `<button id="chef-mate-instructions-anchor" onclick="openInstructions('${instructionsString}')" class='chef-mate-instructions-button'>ChefMate Instructions</button>`;
            html += "</div>";
            html += "</div>";

        }

        if (result) {
           loadingSpinner.style.display = "none";
           renderingSection.innerHTML = html;
        } else {
            loadingSpinner.style.display = "none";
            this.errorHandler("Error doing searchByNutrients!  Try again...");
        }
    }

    async onSearchByIngredients(event) {
        event.preventDefault();

        let loadingSpinner = document.getElementById("spinner");

        loadingSpinner.style.display = "block";

        let renderingSection = document.getElementById("rendering-recipes-section");

        let ingredientsQuery = document.getElementById("search-ingredients-field").value;

        // needs userId data
        let result = await this.client.searchByIngredients(userId, ingredientsQuery, this.errorHandler);
        this.dataStore.set("searchByIngredients", result);

        let recipesReturned = this.dataStore.get("searchByIngredients");

        let html = "";

        for (let recipe of recipesReturned) {
            let instructionsString = recipe.instructions;


            if (instructionsString === "undefined" || instructionsString == null) {
                instructionsString = "Oops! It seems like we have encountered a recipe without instructions. Please refer to the link instead!"
            } else {
                instructionsString = instructionsString.split("'").join('');
            }

            html += "<div class='recipes'>";
            html += "<div class='content'>";
            html += `<img class="recipe-image" src=${recipe.image}>`;
            html += "<div class='recipe-title'>";
            html += `<p><strong>${recipe.title}</strong></p>`;
            html += "</div>";
            html += "<div class='recipe-content'>";
            html += "<ul>";
            html += `<li>Recipe ID: ${recipe.id}</li>`;
            html += `<li>Ready In: ${recipe.readyInMinutes} minutes</li>`;
            html += `<li>Servings: ${recipe.servings}</li>`;
            html += "</ul>";
            html += "</div>";
            html += `<a href=${recipe.sourceUrl} rel="noopener noreferrer" target="_blank"><button class='instructions-button'>Instructions</button></a>`;
            html += `<button id="chef-mate-instructions-anchor" onclick="openInstructions('${instructionsString}')" class='chef-mate-instructions-button'>ChefMate Instructions</button>`;
            html += "</div>";
            html += "</div>";

        }


        if (result) {
           loadingSpinner.style.display = "none";
           renderingSection.innerHTML = html;
        } else {
            loadingSpinner.style.display = "none";
            this.errorHandler("Error doing searchByIngredients!  Try again...");
        }


    }

    async onGetAllRecipes(event) {
        event.preventDefault();

        let loadingSpinner = document.getElementById("spinner");

        loadingSpinner.style.display = "block";

        let renderingSection = document.getElementById("rendering-recipes-section");

        let query = document.getElementById("get-all-search-field").value;
        console.log(query);

        // needs userId data
        let result = await this.client.getAllRecipes(userId, query, this.errorHandler);
        this.dataStore.set("getAllRecipes", result);
        console.log(result);

        let recipesReturned = this.dataStore.get("getAllRecipes");

        let html = "";

        for (let recipe of recipesReturned) {
            let instructionsString = recipe.instructions;


            if (instructionsString === "undefined" || instructionsString == null) {
                instructionsString = "Oops! It seems like we have encountered a recipe without instructions. Please refer to the link instead!"
            } else {
                instructionsString = instructionsString.split("'").join('');
            }

            html += "<div class='recipes'>";
            html += "<div class='content'>";
            html += `<img class="recipe-image" src=${recipe.image}>`;
            html += "<div class='recipe-title'>";
            html += `<p><strong>${recipe.title}</strong></p>`;
            html += "</div>";
            html += "<div class='recipe-content'>";
            html += "<ul>";
            html += `<li>Recipe ID: ${recipe.id}</li>`;
            html += `<li>Ready In: ${recipe.readyInMinutes} minutes</li>`;
            html += `<li>Servings: ${recipe.servings}</li>`;
            html += "</ul>";
            html += "</div>";
            html += `<a href=${recipe.sourceUrl} rel="noopener noreferrer" target="_blank"><button class='instructions-button'>Instructions</button></a>`;
            html += `<button id="chef-mate-instructions-anchor" onclick="openInstructions('${instructionsString}')" class='chef-mate-instructions-button'>ChefMate Instructions</button>`;
            html += "</div>";
            html += "</div>";

        }


        if (result) {
            loadingSpinner.style.display = "none";
            renderingSection.innerHTML = html;
        } else {
            loadingSpinner.style.display = "none";
            this.errorHandler("Error doing getAllRecipes!  Try again...");
        }
    }

    async onGetRandomRecipe(event) {
        event.preventDefault();

        let loadingSpinner = document.getElementById("spinner");

        loadingSpinner.style.display = "block";

        let renderingSection = document.getElementById("rendering-recipes-section");

        // needs userId data
        let result = await this.client.getRandomRecipe(userId, this.errorHandler);
        this.dataStore.set("getRandomRecipe", result);

        let recipesReturned = this.dataStore.get("getRandomRecipe");

        let html = "";

        for (let recipe of recipesReturned) {
            let instructionsString = recipe.instructions;


            if (instructionsString === "undefined" || instructionsString == null) {
                instructionsString = "Oops! It seems like we have encountered a recipe without instructions. Please refer to the link instead!"
            } else {
                instructionsString = instructionsString.split("'").join('');
            }

            html += "<div class='recipes'>";
            html += "<div class='content'>";
            html += `<img class="recipe-image" src=${recipe.image}>`;
            html += "<div class='recipe-title'>";
            html += `<p><strong>${recipe.title}</strong></p>`;
            html += "</div>";
            html += "<div class='recipe-content'>";
            html += "<ul>";
            html += `<li>Recipe ID: ${recipe.id}</li>`;
            html += `<li>Ready In: ${recipe.readyInMinutes} minutes</li>`;
            html += `<li>Servings: ${recipe.servings}</li>`;
            html += "</ul>";
            html += "</div>";
            html += `<a href=${recipe.sourceUrl} rel="noopener noreferrer" target="_blank"><button class='instructions-button'>Instructions</button></a>`;
            html += `<button id="chef-mate-instructions-anchor" onclick="openInstructions('${instructionsString}')" class='chef-mate-instructions-button'>ChefMate Instructions</button>`;
            html += "</div>";
            html += "</div>";

        }


        if (result) {
            loadingSpinner.style.display = "none";
            renderingSection.innerHTML = html;
        } else {
            loadingSpinner.style.display = "none";
            this.errorHandler("Error doing getAllRecipes!  Try again...");
        }
    }

    async onUpdateUserPreference(event) {
        event.preventDefault();
        let userId = this.dataStore.get("userId");
        const textInput = document.getElementById("user-dietary-preference-field").value;
        const updatePreferences = await this.client.updateUserPreference(userId, textInput, this.errorHandler);
        if (!updatePreferences.data) {
            this.showMessage(`Updated users dietary preferences`);
        } else {
            this.errorHandler("Error updating user dietary preferences!  Try again...");
        }
    }

    async onUpdateRecipesTried(event) {
        event.preventDefault();
        let userId = this.dataStore.get("userId");
        const textInput = document.getElementById("recipes-user-tried-field").value;
        const updateRecipesTried = await this.client.updateRecipesTried(userId, textInput, this.errorHandler);
        if (!updateRecipesTried.data) {
            this.showMessage(`Updated Recipes Tried`);
        } else {
            this.errorHandler("Error updating recipes tried!  Try again...");
        }
    }

    async onDeleteUser(event) {
        event.preventDefault();

        let userId = this.dataStore.get("userId");
        const deleteUser = await this.client.deleteUser(userId, this.errorHandler);
        if (!deleteUser.data) {
            this.showMessage(`Deleted User from database!`);
        } else {
            this.errorHandler("Error deleting user!  Try again...");
        }
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);