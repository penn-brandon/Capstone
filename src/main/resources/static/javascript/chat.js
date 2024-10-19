//<div className="profile-dropdown" id="profile-dropdown">
  //  <a href="${pageContext.request.contextPath}/profile" id="profile-link">Profile</a>
    //<a href="${pageContext.request.contextPath}/logout" id="logout-link">Logout</a>
//x</div>


function profile_click(route) {
    if(document.getElementById('profile-dropdown')){
        document.getElementById('profile-dropdown').remove();
    }
    else {
        const profile_dropdown = document.createElement("div");
        profile_dropdown.id = 'profile-dropdown';
        profile_dropdown.className = 'profile-dropdown';

        const profile = document.createElement('a');
        const logout = document.createElement('a');

        profile.href = route + "/profile";
        profile.id = "profile-link";
        profile.innerHTML = "Profile";

        logout.href = route + "/logout";
        logout.id = "logout-link";
        logout.innerHTML = "Logout";

        profile_dropdown.appendChild(profile);
        profile_dropdown.appendChild(logout);

        document.getElementById("profile-div").appendChild(profile_dropdown);
    }

}