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
export default class ChefMateClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'addNewUser', 'updateUserPreference', 'updateRecipesTried', 'deleteUser'];
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
     * Makes a request to the backend to save the authenticated user to the ChefMateUser database.
     * @param userId Id of the authenticated user to save.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The response after saving the user
     */
    async addNewUser(userId, errorCallback) {
        try{
            const response = await this.client.post(`/user/createUser`, {
                userId: userId});
            return response.data;
        } catch (error) {
            this.handleError("addNewUser", error, errorCallback);
        }
    }

    /**
     * Makes a request to the backend to update the list of the users dietary preferences.
     * @param userId Id of the authenticated user to update preferences for.
     * @param userPreferences Dietary preferences of the user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of preferences retrieved
     */
    async updateUserPreference(userId, userPreferences, errorCallback) {
        try{
            const response = await this.client.put(`/user/userPreferences/${userPreferences}`, {
                userId: userId,
                userPreferences: userPreferences});
            return response.data;
        } catch (error) {
            this.handleError("updateUserPreference", error, errorCallback);
        }
    }

    /**
     * Makes a request to the backend to update the set of recipes tried by the authenticated user.
     * @param userId Id of the authenticated user to update the recipes tried.
     * @param recipesTried Dietary preferences of the user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The set of recipes tried by the user is retrieved
     */
    async updateRecipesTried(userId, recipesTried, errorCallback) {
        try{
            const response = await this.client.put(`/user/recipesTried/${recipesTried}`, {
                userId: userId,
                recipesTried: recipesTried});
            return response.data;
        } catch (error) {
            this.handleError("updateRecipesTried", error, errorCallback);
        }
    }

    /**
     * Makes a request to the backend to delete the user record from the ChefMateUser database.
     * @param userId Id of the authenticated user to update the recipes tried.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The set of recipes tried by the user is retrieved
     */
    async deleteUser(userId, errorCallback) {
        try{
            const response = await this.client.delete(`/user/deleteUser/${userId}`);
            return response.data;
        } catch (error) {
            this.handleError("deleteUser", error, errorCallback);
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
