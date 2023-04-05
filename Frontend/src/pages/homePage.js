import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import RecipeClient from "../api/recipeClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class HomePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onSearchByNutrients'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('search-by-nutrients-form').addEventListener('submit', this.onSearchByNutrients);
        this.client = new RecipeClient();

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
            this.errorHandler("Error doing searchByNutrients!  Try again...");
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
