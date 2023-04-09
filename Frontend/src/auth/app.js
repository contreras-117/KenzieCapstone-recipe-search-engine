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


// // Will run when page finishes loading
// window.onload = async () => {
//   await configureClient();
//
//   // If unable to parse the history hash, default to the root URL
//   if (!showContentFromUrl(window.location.pathname)) {
//     showContentFromUrl("/");
//     window.history.replaceState({ url: "/" }, {}, "/");
//   }
//
//   const bodyElement = document.getElementsByTagName("body")[0];
//
//   // Listen out for clicks on any hyperlink that navigates to a #/ URL
//   bodyElement.addEventListener("click", (e) => {
//     if (isRouteLink(e.target)) {
//       const url = e.target.getAttribute("href");
//
//       if (showContentFromUrl(url)) {
//         e.preventDefault();
//         window.history.pushState({ url }, {}, url);
//       }
//     }
//   });
//
//   const isAuthenticated = await auth0Client.isAuthenticated();
//
//   if (isAuthenticated) {
//     console.log("> User is authenticated");
//     window.history.replaceState({}, document.title, window.location.pathname);
//     updateUI();
//     return;
//   }
//
//   console.log("> User not authenticated");
//
//   const query = window.location.search;
//   const shouldParseResult = query.includes("code=") && query.includes("state=");
//
//   if (shouldParseResult) {
//     console.log("> Parsing redirect");
//     try {
//       const result = await auth0Client.handleRedirectCallback();
//
//       if (result.appState && result.appState.targetUrl) {
//         showContentFromUrl(result.appState.targetUrl);
//       }
//
//       console.log("Logged in!");
//     } catch (err) {
//       console.log("Error parsing redirect:", err);
//     }
//
//     window.history.replaceState({}, document.title, "/");
//   }
//
//   updateUI();
// };

// const updateUI = async () => {
//   const isAuthenticated = await auth0Client.isAuthenticated();
//   const userProfile = await auth0Client.getUser();
//
//   document.getElementById("logout").disabled = !isAuthenticated;
//   document.getElementById("login").disabled = isAuthenticated
//   // Use the element with id "profile" in the DOM
//   const profileElement = document.getElementById("profile");
//
//   if (isAuthenticated) {
//     profileElement.style.display = "block";
//     profileElement.innerHTML = `
//             <p>${userProfile.name}</p>
//             <img src="${userProfile.picture}" />
//           `;
//   } else {
//     profileElement.style.display = "none";
//   }
// };


// const updateUI = async () => {
//
//   try {
//     const isAuthenticated = await auth0Client.isAuthenticated();
//     //const userProfile = await auth0Client.getUser();
//
//     // document.getElementById("logout").disabled = !isAuthenticated;
//     // document.getElementById("login").disabled = isAuthenticated
//
//     const logout = document.getElementById("logout");
//     const login = document.getElementById("login");
//     // Use the element with id "profile" in the DOM
//     //const profileElement = document.getElementById("profile");
//
//     if (!isAuthenticated) {
//       login.innerHTML = "Login";
//       login.onclick = login;
//     }
//
//     else if (isAuthenticated) {
//       const user = await auth0Client.getUser();
//
//
//       // profileElement.style.display = "block";
//       // profileElement.innerHTML = `
//       //       <p>${userProfile.name}</p>
//       //       <img src="${userProfile.picture}" />
//       //     `;
//
//       document.getElementById("profile-data").innerText = JSON.stringify(
//           user,
//           null,
//           2
//       );
//
//       document.querySelectorAll("pre code").forEach(hljs.highlightBlock);
//
//       eachElement(".profile-image", (e) => (e.src = user.picture));
//       eachElement(".user-name", (e) => (e.innerText = user.name));
//       eachElement(".user-email", (e) => (e.innerText = user.email));
//       eachElement(".auth-invisible", (e) => e.classList.add("hidden"));
//       eachElement(".auth-visible", (e) => e.classList.remove("hidden"));
//     } else {
//       eachElement(".auth-invisible", (e) => e.classList.remove("hidden"));
//       eachElement(".auth-visible", (e) => e.classList.add("hidden"));
//       //profileElement.style.display = "none";
//     }
//   } catch (err) {
//     console.log("Error updating UI!", err);
//     return;
//   }
//
//   console.log("UI updated");
// };


const updateUI = async () => {
  const isAuthenticated = await auth0Client.isAuthenticated();
  const login = document.getElementById("login");
  const logout = document.getElementById("logout");

  if (!isAuthenticated) {
    login.innerHTML = "Login";
    login.onclick = login;
  }

  else if (isAuthenticated) {
    const user = await auth0Client.getUser();
    logout.innerHTML = "Logout";
    logout.onclick = logout;
    //display username if user is logged in
    document.getElementById('content-profile').innerHTML = `${user.nickname}`;
    const accountLink = document.getElementById("account-link")
    accountLink.addEventListener("click", account);
    accountLink.style.display = 'block';
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


// /**
//  * Starts the authentication flow
//  */
// const login = async (targetUrl) => {
//   try {
//     console.log("Logging in", targetUrl);
//
//     const options = {
//       authorizationParams: {
//         redirect_uri: window.location.origin
//       }
//     };
//
//     if (targetUrl) {
//       options.appState = { targetUrl };
//     }
//
//     await auth0Client.loginWithRedirect(options);
//   } catch (err) {
//     console.log("Log in failed", err);
//   }
// };

// /**
//  * Executes the logout flow
//  */
// const logout = async () => {
//   try {
//     console.log("Logging out");
//     await auth0Client.logout({
//       logoutParams: {
//         returnTo: window.location.origin
//       }
//     });
//   } catch (err) {
//     console.log("Log out failed", err);
//   }
// };