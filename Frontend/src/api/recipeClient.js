import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the ChefMateUserService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class RecipeClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'searchByNutrients', 'searchByIngredients', 'getAllRecipes'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets recipes for the given query string of nutrients.
     * @param query Spoonacular API queries
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns List of recipes
     */
    async searchByNutrients(userId, query, errorCallback) {
        try {
            const response = await this.client.get(`/user/${userId}/recipes/food/search/nutrients/${query}`);
            return response.data;
        } catch (error) {
            document.getElementById("spinner").style.display = "none";
            this.handleError("searchByNutrients", error, errorCallback);
        }
    }
    /**
     * Gets recipes for the given query string of ingredients.
     * @param query Spoonacular API queries
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns List of recipes
     */
     async searchByIngredients(userId, query, errorCallback) {
         try {
             const response = await this.client.get(`/user/${userId}/recipes/food/search/ingredients/${query}`);
             return response.data;
         } catch (error) {
             document.getElementById("spinner").style.display = "none";
             this.handleError("searchByIngredients", error, errorCallback);
         }
     }

    async getAllRecipes(userId, query, errorCallback) {
        try{
            const response = await this.client.get(`/user/${userId}/recipes/food/search/${query}`);
            return response.data;
        } catch (error) {
            this.handleError("getAllRecipes", error, errorCallback);
        }
    }

    async getRandomRecipe(userId, errorCallback) {
        try{
            const response = await this.client.get(`/user/${userId}/recipes/random`);
            return response.data;
        } catch (error) {
            this.handleError("getRandomRecipe", error, errorCallback);
        }
    }
    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
