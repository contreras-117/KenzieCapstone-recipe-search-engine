
auth0.createAuth0Client({
  domain: "dev-fj42itvjlehoyi5w.us.auth0.com",
  clientId: "JJkHXPg0fwgPSaOmo1V555VNnqOv7VN9",
  authorizationParams: {
    redirect_uri: window.location.origin
  }
}).then(async (auth0Client) => {
  // Use the login link with id "login" in the DOM
  const loginLink = document.getElementById("login");
  console.log(loginLink)

  loginLink.addEventListener("click", (e) => {
    e.preventDefault();
    auth0Client.loginWithRedirect();
  });

  if (location.search.includes("state=") && 
      (location.search.includes("code=") || 
      location.search.includes("error="))) {
    await auth0Client.handleRedirectCallback();
    window.history.replaceState({}, document.title, "/");
  }

  // Use the logout link with id "logout" in the DOM
  const logoutLink = document.getElementById("logout");

  logoutLink.addEventListener("click", (e) => {
    e.preventDefault();
    auth0Client.logout();
  });

  const isAuthenticated = await auth0Client.isAuthenticated();
  const userProfile = await auth0Client.getUser();

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
});