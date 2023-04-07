import { createAuth0Client } from '@auth0/auth0-spa-js';

let auth0Client = null;

const configureClient = async () => {
  auth0Client = await createAuth0Client({
    domain: process.env.CAPSTONE_DOMAIN,
    clientId: process.env.CAPSTONE_CLIENT_ID,
  });
};

window.onload = async () => {
  await configureClient();
  // Update the UI state
  await updateUI();

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


const login = async () => {
  await auth0Client.loginWithRedirect({
    authorizationParams: {
      redirect_uri: window.location.origin
    }
  });
};

const logout = () => {
  auth0Client.logout({
    logoutParams: {
      returnTo: window.location.origin
    }
  });
};
