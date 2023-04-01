import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import RecipeClient from "../api/recipeClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class ExamplePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onSearchByNutrients'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('search-by-nutrients-form').addEventListener('submit', this.onGet);
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

        let query = "{";

        if (minCarbs !== "undefined" && minCarbs !== "") {
            query += "'minCarbs': " + minCarbs + ",";
        }
        if (maxCarbs !== "undefined" && maxCarbs !== "") {
            query += "'maxCarbs': " + maxCarbs + ",";;
        }
        if (minProtein !== "undefined" && minProtein !== "") {
            query += "'minProtein': " + minProtein + ",";
        }
        if (maxProtein !== "undefined" && maxProtein !== "") {
            query += "'maxProtein': " + maxProtein + ",";
        }
        if (minCalories !== "undefined" && minCalories !== "") {
            query += "'minCalories': " + minCalories + ",";
        }
        if (maxCalories !== "undefined" && maxCalories !== "") {
            query += "'maxCalories': " + maxCalories + ",";
        }
        if (minSodium !== "undefined" && minSodium !== "") {
            query += "'minSodium': " + minSodium + ",";
        }
        if (maxSodium !== "undefined" && maxSodium !== "") {
            query += "'maxSodium': " + maxSodium + ",";
        }
        if (minSugar !== "undefined" && minSugar !== "") {
            query += "'minSugar': " + minSugar + ",";
        }
        if (maxSugar !== "undefined" && maxSugar !== "") {
            query += "'maxSugar': " + maxSugar + ",";
        }
        if (minFiber !== "undefined" && minFiber !== "") {
            query += "'minFiber': " + minFiber + ",";
        }
        if (maxFiber !== "undefined" && maxFiber !== "") {
            query += "'maxFiber': " + maxFiber + ",";
        }
        if (minIron !== "undefined" && minIron !== "") {
            query += "'maxFiber': " + minIron + ",";
        }
        if (maxIron !== "undefined" && maxIron !== "") {
            query += "'maxIron': " + maxIron + ",";
        }
        if (minCholesterol !== "undefined" && minCholesterol !== "") {
            query += "'minCholesterol': " + minCholesterol + ",";
        }
        if (maxCholesterol !== "undefined" && maxCholesterol !== "") {
            query += "'maxCholesterol': " + maxCholesterol + ",";
        }

        if (query.charAt(query.length - 1) == ",") {
            query = query.slice(0, query.length - 1);
        }


        query += "}";

        let result = await this.client.searchByNutrients(query, this.errorHandler);
        this.dataStore.set("searchByNutrients", result);

        let renderingSection = document.getElementById("rendering-recipes-section");

        let recipesReturned = this.dataStore.get("searchByNutrients");

        let html = "";

        for (let recipe of recipesReturned) {

            html += "<div class='recipes'>";
            html += "<div class='content'>";
            html += `<img class="recipe-image" src=${recipe.image}>`;
            html += "<div class='recipe-title'>";
            html += `<h5>${recipe.title}</h5>`;
            html += "</div>";
            html += "<div class='recipe-content'>";
            html += "<ul>";
            html += `<li>Recipe ID: ${recipe.id}</li>`;
            html += `<li>ETA: ${recipe.readyInMinutes}</li>`;
            html += `<li>Servings: ${recipe.servings}</li>`;
            html += "</ul>";
            html += "</div>";
            html += `<button onclick="window.open("${recipe.sourceUrl}",'_blank')" class="instructions-button">Instructions</button>`;
            html += "</div>";
            html += "</div>";

        }

        if (result) {
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
