import { createAuth0Client } from '@auth0/auth0-spa-js';
//import * as utils from 'ui.js';
//import {updateUI} from "./ui";
import './ui';
import ChefMateClient from "../api/chefMateClient";
import DataStore from "../util/DataStore";


let auth0Client = null;

const configureClient = async () => {
  auth0Client = await createAuth0Client({
    domain: process.env.CAPSTONE_DOMAIN,
    clientId: process.env.CAPSTONE_CLIENT_ID,
    authorizationParams: {
      redirect_uri: window.location.origin
    }
  });
};


window.onload = async () => {
  console.log("About to call configureClient");
  await configureClient();
  // Update the UI state
  //await updateUI();

  // Check for the code and state parameters
  const query = window.location.search;
  if (query.includes("state=") &&
      (query.includes("code=") ||
          query.includes("error="))) {

    // Process the login state
    await auth0Client.handleRedirectCallback();

    await updateUI();

    // Use replaceState to redirect the user and remove the querystring parameters
    window.history.replaceState({}, document.title, "/");
  }
};

//redirect to the Universal Login Page
document.getElementById('login').addEventListener('click', async () => {
  await auth0Client.loginWithRedirect();
});

//in your callback route (<MY_CALLBACK_URL>)
window.addEventListener('load', async () => {
  const redirectResult = await auth0Client.handleRedirectCallback();
  console.log(redirectResult);
  //logged in. you can get the user profile like this:
  const user = await auth0Client.getUser();
  console.log(user);
  // save the userId (user email) in our database
  const result = await addNewUser(user.email);
  // Also save it to the frontend datastore for easy access
  this.dataStore.set("userId", user.email);
});


const updateUI = async () => {
  const isAuthenticated = await auth0Client.isAuthenticated();
  const userProfile = await auth0Client.getUser();

  document.getElementById("logout").disabled = !isAuthenticated;
  document.getElementById("login").disabled = isAuthenticated
  // Use the element with id "profile" in the DOM
  const profileElement = document.getElementById("profile");

  if (isAuthenticated) {
    profileElement.style.display = "block";
    profileElement.innerHTML = `
            <p>${userProfile.name}</p>
            <img src="${userProfile.picture}" />
          `;
  } else {
    profileElement.style.display = "none";
  }
};

document.getElementById('logout').addEventListener('click', () => {
  auth0Client.logout();
});

auth0Client.logout({
  logoutParams: {
    returnTo: window.location.origin
  }
});
